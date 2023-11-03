package com.eteration.simplebanking.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object for creating a new account.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatedAccountRequest {

    /**
     * The name of the account owner.
     */
    private String owner;

}
