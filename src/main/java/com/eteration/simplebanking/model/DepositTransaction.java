package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("DepositTransaction")
public class DepositTransaction extends Transaction {

    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public void executeOn(Account account) throws InsufficientBalanceException {
        account.deposit(this.getAmount());
        account.getTransactions().add(this);
    }
}
