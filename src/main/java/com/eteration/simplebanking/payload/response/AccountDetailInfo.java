package com.eteration.simplebanking.payload.response;

import com.eteration.simplebanking.dto.TransactionDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Response object providing detailed information about an account.
 */
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDetailInfo {

    /**
     * The account number.
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
