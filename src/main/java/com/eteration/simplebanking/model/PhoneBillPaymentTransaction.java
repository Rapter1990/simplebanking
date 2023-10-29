package com.eteration.simplebanking.model;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("PhoneBillPaymentTransaction")
public class PhoneBillPaymentTransaction extends WithdrawalTransaction {

    private String payee;

    public PhoneBillPaymentTransaction(String payee, double amount) {
        super(amount);
        this.payee = payee;
    }

}
