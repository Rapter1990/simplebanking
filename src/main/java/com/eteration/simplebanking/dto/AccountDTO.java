package com.eteration.simplebanking.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private String accountNumber;
    private String owner;
    private Double balance;
    private LocalDateTime createdDateTime;
    List<TransactionDTO> transactionDTOs;
}
