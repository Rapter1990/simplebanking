package com.eteration.simplebanking.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object for creating a withdrawal transaction.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateWithdrawalRequest {

    /**
     * The account number from which the withdrawal is made.
     */
    private String accountNumber;

    /**
     * The amount of the withdrawal.
     */
    private Double amount;

}
