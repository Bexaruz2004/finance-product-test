package com.example.financeproduct.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDTO {
    @NotNull
    private Long fromUserId;

    @NotNull
    private Long toUserId;

    @NotNull
    @DecimalMin(value = "0.01", message = "The amount must be greater than zero")
    private BigDecimal amount;
}
