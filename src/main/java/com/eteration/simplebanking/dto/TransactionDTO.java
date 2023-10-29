package com.eteration.simplebanking.dto;

import com.eteration.simplebanking.model.enums.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDTO {

    private Double amount;
    private TransactionType transactionType;
    private String approvalCode;
    private LocalDateTime createdDateTime;
}
