package com.example.financeproduct.config;

import com.example.financeproduct.entity.User;
import com.example.financeproduct.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            List<User> users = List.of(
                    User.builder()
                            .username("user-1")
                            .email("user-1@gmail.com")
                            .pass(passwordEncoder.encode("1111"))
                            .balance(new BigDecimal("1000.00"))
                            .build(),
                    User.builder()
                            .username("user-2")
                            .email("user-2@gmail.com")
                            .pass(passwordEncoder.encode("2222"))
                            .balance(new BigDecimal("800.00"))
                            .build(),
                    User.builder()
                            .username("user-3")
                            .email("user-3@gmail.com")
                            .pass(passwordEncoder.encode("3333"))
                            .balance(new BigDecimal("1000.00"))
                            .build()
            );

            userRepository.saveAll(users);
            System.out.println("DataLoader initialize)");
        }
    }
}
