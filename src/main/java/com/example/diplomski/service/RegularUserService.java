package com.example.diplomski.service;

import com.example.diplomski.exceptions.UserNotFoundException;
import com.example.diplomski.model.RegularUser;
import com.example.diplomski.repository.RegularUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegularUserService {
    private RegularUserRepository regularUserRepository;

    public RegularUser getByEmail(String email) throws UserNotFoundException {
        return regularUserRepository.findById(email).orElseThrow(() -> new UserNotFoundException("Regular user not found."));
    }

    public void save(RegularUser nutritionist) {
        regularUserRepository.save(nutritionist);
    }
}
