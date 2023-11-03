package com.eteration.simplebanking.model.enums;

/**
 * Enum representing different types of transactions in the Simple Banking App.
 */
public enum TransactionType {

    /**
     * Represents a deposit transaction.
     */
    DepositTransaction("DepositTransaction"),

    /**
     * Represents a withdrawal transaction.
     */
    WithdrawalTransaction("WithdrawalTransaction"),

    /**
     * Represents a phone bill payment transaction.
     */
    PhoneBillPaymentTransaction("PhoneBillPaymentTransaction");

    /**
     * Represents the discriminator value associated with the transaction type.
     */
    private String discriminatorValue;

    /**
     * Constructor for TransactionType.
     *
     * @param discriminatorValue The discriminator value associated with the transaction type.
     */
    TransactionType(String discriminatorValue) {
        this.discriminatorValue = discriminatorValue;
    }

    /**
     * Gets the discriminator value associated with the transaction type.
     *
     * @return The discriminator value.
     */
    public String getDiscriminatorValue() {
        return discriminatorValue;
    }

    /**
     * Converts a discriminator value to the corresponding TransactionType enum.
     *
     * @param value The discriminator value to convert.
     * @return The TransactionType enum corresponding to the given value.
     * @throws IllegalArgumentException if the provided value is unknown.
     */
    public static TransactionType fromDiscriminatorValue(String value) {
        for (TransactionType type : values()) {
            if (type.getDiscriminatorValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown discriminator value: " + value);
    }
}
