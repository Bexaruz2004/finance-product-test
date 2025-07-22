package com.example.financeproduct.controller;

import com.example.financeproduct.DTO.LoginDTO;
import com.example.financeproduct.DTO.UserDTO;
import com.example.financeproduct.entity.User;
import com.example.financeproduct.payload.ApiResponse;
import com.example.financeproduct.payload.AuthResponse;
import com.example.financeproduct.repository.UserRepository;
import com.example.financeproduct.security.JwtService;
import com.example.financeproduct.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody User user) {
        User created = userService.register(user);
        UserDTO userDTO = UserDTO.builder().username(created.getUsername()).balance(user.getBalance()).build();
        return ApiResponse.success("User registered successfully", userDTO);
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody LoginDTO dto) {
        User user = userService.login(dto);
        String accessToken = jwtService.generateToken(user);
        return ApiResponse.success("Login successful", new AuthResponse(accessToken));
    }
}
