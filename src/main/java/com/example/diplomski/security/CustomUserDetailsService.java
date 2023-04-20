package com.example.diplomski.security;

import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public UserDetails loadUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
