package com.example.financeproduct.controller;

import com.example.financeproduct.DTO.TransferRequestDTO;
import com.example.financeproduct.entity.Transaction;
import com.example.financeproduct.payload.ApiResponse;
import com.example.financeproduct.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/transfer")
    public ApiResponse<?> transfer(@RequestBody @Valid TransferRequestDTO requestDTO){
        transactionService.transfer(requestDTO);
        return ApiResponse.success("The transfer was completed successfully :)");
    }

    @GetMapping
    public ApiResponse<?> getTransactions(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to
    ) {
        List<Transaction> transactions = transactionService.getTransactions(userId, from, to);
        return ApiResponse.success(transactions);
    }
}
