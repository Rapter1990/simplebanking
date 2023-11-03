package com.eteration.simplebanking.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing an account in the Simple Banking App.
 */
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDTO {

    /**
     * The unique account number associated with the account.
     */
    private String accountNumber;

    /**
     * The name of the account owner.
     */
    private String owner;

    /**
     * The current balance of the account.
     */
    private Double balance;

    /**
     * The date and time when the account was created.
     */
    private LocalDateTime createdDateTime;

    /**
     * A list of transaction DTOs associated with this account.
     */
    List<TransactionDTO> transactionDTOs;

}
