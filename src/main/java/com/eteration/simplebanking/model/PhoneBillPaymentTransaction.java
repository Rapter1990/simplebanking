package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Represents a phone bill payment transaction in the Simple Banking App, extending WithdrawalTransaction.
 */
@Entity
@NoArgsConstructor
@DiscriminatorValue("PhoneBillPaymentTransaction")
public class PhoneBillPaymentTransaction extends WithdrawalTransaction {

    /**
     * Constructs a PhoneBillPaymentTransaction with the specified amount.
     *
     * @param amount The amount to pay for a phone bill.
     */
    public PhoneBillPaymentTransaction(double amount) {
        super(amount);
    }

}
