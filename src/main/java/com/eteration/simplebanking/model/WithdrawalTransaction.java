package com.eteration.simplebanking.model;


import com.eteration.simplebanking.exception.InsufficientBalanceException;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Represents a withdrawal transaction in the Simple Banking App.
 */
@Entity
@NoArgsConstructor
@DiscriminatorValue("WithdrawalTransaction")
public class WithdrawalTransaction extends Transaction {

    /**
     * Constructs a WithdrawalTransaction with the specified amount.
     *
     * @param amount The amount to withdraw.
     */
    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    /**
     * Executes the withdrawal transaction on the specified account, adding the transaction to the account's transaction list.
     *
     * @param account The account on which the withdrawal transaction is executed.
     * @throws InsufficientBalanceException if there is an issue with insufficient balance.
     */
    @Override
    public void executeOn(Account account) throws InsufficientBalanceException {
        account.withdraw(this.getAmount());
        account.getTransactions().add(this);
    }

}


