package com.example.financeproduct.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    @NotNull
    private String username;

    @NotNull
    private BigDecimal balance;
}
