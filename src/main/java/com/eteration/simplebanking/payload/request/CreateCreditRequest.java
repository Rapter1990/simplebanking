package com.eteration.simplebanking.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object for creating a credit transaction in the Simple Banking App.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCreditRequest {

    /**
     * The account number to which the credit is applied.
     */
    private String accountNumber;

    /**
     * The amount of credit to be added to the account.
     */
    private Double amount;

}
