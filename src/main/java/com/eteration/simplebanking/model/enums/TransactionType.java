package com.eteration.simplebanking.model.enums;

public enum TransactionType {
    DepositTransaction("DepositTransaction"),
    WithdrawalTransaction("WithdrawalTransaction"),
    PhoneBillPaymentTransaction("PhoneBillPaymentTransaction");

    private String discriminatorValue;

    TransactionType(String discriminatorValue) {
        this.discriminatorValue = discriminatorValue;
    }

    public String getDiscriminatorValue() {
        return discriminatorValue;
    }

    public static TransactionType fromDiscriminatorValue(String value) {
        for (TransactionType type : values()) {
            if (type.getDiscriminatorValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown discriminator value: " + value);
    }
}
