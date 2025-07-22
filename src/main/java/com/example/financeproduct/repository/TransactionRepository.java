package com.example.financeproduct.repository;

import com.example.financeproduct.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllByUserId(Long id);
    List<Transaction> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to);
    List<Transaction> findByUserIdAndCreatedAtBetween(Long userId, LocalDateTime from, LocalDateTime to);
}
