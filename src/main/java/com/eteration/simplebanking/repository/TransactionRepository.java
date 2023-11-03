package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing transactions in the Simple Banking App.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
