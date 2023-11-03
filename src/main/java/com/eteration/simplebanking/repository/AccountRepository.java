package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing accounts in the Simple Banking App.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Retrieves an account by its account number.
     *
     * @param accountNumber The unique account number to search for.
     * @return An Optional containing the account if found, or an empty Optional if not found.
     */
    Optional<Account> findByAccountNumber(String accountNumber);

}
