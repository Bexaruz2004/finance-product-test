package com.example.financeproduct.service;

import com.example.financeproduct.DTO.TransferRequestDTO;
import com.example.financeproduct.entity.Transaction;
import com.example.financeproduct.entity.User;
import com.example.financeproduct.exceptions.EntityNotFoundException;
import com.example.financeproduct.repository.TransactionRepository;
import com.example.financeproduct.repository.UserRepository;
import com.example.financeproduct.utils.TransactionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final UserRepository userRepo;
    private final TransactionRepository transactionRepo;

    @Transactional
    public void transfer(TransferRequestDTO requestDTO){
        if(requestDTO.getFromUserId().equals(requestDTO.getToUserId()))
            throw new IllegalArgumentException("You can't transfer to yourself");

        User sndr = userRepo.findById(requestDTO.getFromUserId()).orElseThrow(() -> new EntityNotFoundException("User", "id", requestDTO.getFromUserId()));
        User rcvr = userRepo.findById(requestDTO.getToUserId()).orElseThrow(() -> new EntityNotFoundException("User", "id", requestDTO.getToUserId()));

        if(sndr.getBalance().compareTo(requestDTO.getAmount()) < 0)
            throw new IllegalArgumentException("Not enough money in your account");

        sndr.setBalance(sndr.getBalance().subtract(requestDTO.getAmount()));
        rcvr.setBalance(rcvr.getBalance().add(requestDTO.getAmount()));

        userRepo.save(sndr);
        userRepo.save(rcvr);

        transactionRepo.save(Transaction.builder()
                .user(sndr)
                .amount(requestDTO.getAmount().negate())
                .createdAt(LocalDateTime.now())
                .transactionType(TransactionType.OUTCOME)
                .build());

        transactionRepo.save(Transaction.builder()
                .user(rcvr)
                .amount(requestDTO.getAmount())
                .createdAt(LocalDateTime.now())
                .transactionType(TransactionType.INCOME)
                .build());
    }

    public List<Transaction> getTransactions(Long userId, LocalDateTime from, LocalDateTime to) {
        if (userId != null && from != null && to != null) {
            return transactionRepo.findByUserIdAndCreatedAtBetween(userId, from, to);
        } else if (userId != null) {
            return transactionRepo.findAllByUserId(userId);
        } else if (from != null && to != null) {
            return transactionRepo.findByCreatedAtBetween(from, to);
        } else {
            return transactionRepo.findAll();
        }
    }

}
