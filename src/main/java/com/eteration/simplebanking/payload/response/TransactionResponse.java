package com.eteration.simplebanking.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response object for a transaction.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {

    /**
     * The status of the transaction.
     */
    private String status;

    /**
     * The approval code associated with the transaction.
     */
    private String approvalCode;

}
