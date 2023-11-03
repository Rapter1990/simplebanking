package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.model.enums.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a transaction in the Simple Banking App.
 */
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDTO {

    /**
     * The transaction amount, which can be positive (credit) or negative (debit).
     */
    private Double amount;

    /**
     * The type of the transaction, such as credit, debit, or other transaction types.
     */
    private TransactionType transactionType;

    /**
     * The approval code associated with the transaction, if applicable.
     */
    private String approvalCode;

    /**
     * The date and time when the transaction was created or processed.
     */
    private LocalDateTime createdDateTime;

}
