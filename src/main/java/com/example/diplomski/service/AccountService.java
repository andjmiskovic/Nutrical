package com.example.diplomski.service;

import com.example.diplomski.config.AppProperties;
import com.example.diplomski.dto.*;
import com.example.diplomski.enums.Role;
import com.example.diplomski.exceptions.EmailAlreadyInUseException;
import com.example.diplomski.exceptions.EmailAlreadyVerifiedException;
import com.example.diplomski.exceptions.InvalidVerificationCodeException;
import com.example.diplomski.model.Nutritionist;
import com.example.diplomski.model.User;
import com.example.diplomski.repository.UserRepository;
import com.example.diplomski.security.TokenAuthenticationFilter;
import com.example.diplomski.security.TokenProvider;
import com.example.diplomski.util.CookieUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private TokenProvider tokenProvider;
    private PasswordEncoder passwordEncoder;
    private AppProperties appProperties;
    private MailingService mailingService;

    private final SecureRandom random = new SecureRandom();

    public LoginResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = tokenProvider.createAccessToken(authentication);
        Role role = ((User) authentication.getPrincipal()).getRole();
        Integer tokenExpirationSeconds = appProperties.getAuth().getTokenExpirationSeconds();
        CookieUtils.addCookie(
                response,
                TokenAuthenticationFilter.ACCESS_TOKEN_COOKIE_NAME,
                accessToken,
                tokenExpirationSeconds
        );

        Long expiresAt = tokenProvider.readClaims(accessToken).getExpiration().getTime();
        return new LoginResponse(accessToken, expiresAt, role);
    }

    public boolean verifyLogin(LoginVerificationRequest verificationRequest, User user) {
        return passwordEncoder.matches(verificationRequest.getPassword(), user.getPassword());
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, TokenAuthenticationFilter.ACCESS_TOKEN_COOKIE_NAME);
    }

    public void registerNutritionist(RegistrationRequest registrationRequest) {
        checkEmailAvailability(registrationRequest.getEmail());

        Nutritionist nutritionist = new Nutritionist();
        nutritionist.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        nutritionist.setRole(Role.NUTRITIONIST);
        nutritionist.setEmail(registrationRequest.getEmail());
        nutritionist.setFirstName(registrationRequest.getFirstName());
        nutritionist.setLastName(registrationRequest.getLastName());
        nutritionist.setEmailVerified(false);
        nutritionist.setVerificationCode(generateVerificationCode());

        userRepository.save(nutritionist);
        mailingService.sendEmailVerificationMail(nutritionist);
    }

    public void verifyEmail(VerificationRequest verificationRequest) {
        User userToVerify = userRepository.findByVerificationCode(verificationRequest.getCode()).orElseThrow(InvalidVerificationCodeException::new);

        if (userToVerify.isEmailVerified())
            throw new EmailAlreadyVerifiedException();

        userToVerify.setEmailVerified(true);
        userRepository.save(userToVerify);
    }

    private void checkEmailAvailability(String email) {
        if (userRepository.existsByEmail(email))
            throw new EmailAlreadyInUseException();
    }

    private String generateVerificationCode() {
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
