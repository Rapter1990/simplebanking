package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "transactionType", source = "transactionType")
    TransactionDTO toTransactionDTO(Transaction transaction);

    List<TransactionDTO> toTransactionDTOList(List<Transaction> transactions);
}
