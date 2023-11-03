package com.eteration.simplebanking.model;



import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * Represents a financial transaction in the Simple Banking App, extended by various transaction types.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The approval code associated with the transaction.
     */
    private String approvalCode;

    /**
     * The amount involved in the transaction.
     */
    private double amount;

    /**
     * The type of the transaction (e.g., deposit, withdrawal).
     */
    @Column(name ="transaction_type" , insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    /**
     * The account associated with the transaction.
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Account account;

    /**
     * Constructs a transaction with the specified amount.
     *
     * @param amount The amount involved in the transaction.
     */
    public Transaction(double amount) {
        this.amount = amount;
    }

    /**
     * Executes the transaction on the specified account. Subclasses must provide the implementation for this method.
     *
     * @param account The account on which the transaction is executed.
     * @throws InsufficientBalanceException if there is an issue with insufficient balance.
     */
    public abstract void executeOn(Account account) throws InsufficientBalanceException;

}
