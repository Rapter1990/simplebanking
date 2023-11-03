package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Represents a deposit transaction in the Simple Banking App.
 */
@Entity
@NoArgsConstructor
@DiscriminatorValue("DepositTransaction")
public class DepositTransaction extends Transaction {

    /**
     * Constructs a DepositTransaction with the specified amount.
     *
     * @param amount The amount to deposit.
     */
    public DepositTransaction(double amount) {
        super(amount);
    }

    /**
     * Executes the deposit transaction on the specified account, adding the transaction to the account's transaction list.
     *
     * @param account The account on which the deposit transaction is executed.
     * @throws InsufficientBalanceException if there is an issue with insufficient balance.
     */
    @Override
    public void executeOn(Account account) throws InsufficientBalanceException {
        account.deposit(this.getAmount());
        account.getTransactions().add(this);
    }

}
