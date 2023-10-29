package com.eteration.simplebanking.payload.response;

import com.eteration.simplebanking.dto.TransactionDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatedAccountResponse {
    private String accountNumber;
    private String owner;
    private Double balance;
    private LocalDateTime createdDateTime;
    List<TransactionDTO> transactionDTOs;
}
