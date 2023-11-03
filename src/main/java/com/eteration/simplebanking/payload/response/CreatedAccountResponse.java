package com.eteration.simplebanking.payload.response;

import com.eteration.simplebanking.dto.TransactionDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Response object for successfully creating an account.
 */
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatedAccountResponse {

    /**
     * The account number of the newly created account.
     */
    private String accountNumber;

    /**
     * The name of the account owner.
     */
    private String owner;

    /**
     * The initial balance of the account.
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
