package com.eteration.simplebanking.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object for creating a phone bill payment transaction.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePhoneBillPaymentRequest {

    /**
     * The account number for which the phone bill payment is made.
     */
    private String accountNumber;

    /**
     * The amount of the phone bill payment.
     */
    private Double amount;

}
