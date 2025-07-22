package com.example.financeproduct.DTO;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
public class LoginDTO {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}
