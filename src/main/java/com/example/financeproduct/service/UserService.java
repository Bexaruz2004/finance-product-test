package com.example.financeproduct.service;

import com.example.financeproduct.DTO.LoginDTO;

import com.example.financeproduct.DTO.UserDTO;
import com.example.financeproduct.entity.User;
import com.example.financeproduct.exceptions.RestException;
import com.example.financeproduct.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public User register(User dto){
        if(userRepo.existsByEmail(dto.getEmail()))
            throw new RestException("Email already exists :)");
        User user = User.builder()
                .email(dto.getEmail())
                .pass(passwordEncoder.encode(dto.getPass()))
                .username(dto.getUsername())
                .balance(dto.getBalance())
                .build();
        return userRepo.save(user);
    }

    public User login(LoginDTO dto) {
        User user = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RestException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPass())) {
            throw new RestException("Incorrect password");
        }

        return user;
    }

}
