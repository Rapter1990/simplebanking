package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper interface for mapping between Transaction entities and DTOs in the Simple Banking App.
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    /**
     * Maps a Transaction entity to a TransactionDTO.
     *
     * @param transaction The source Transaction entity to be mapped.
     * @return A TransactionDTO object.
     */
    @Mapping(target = "transactionType", source = "transactionType")
    TransactionDTO toTransactionDTO(Transaction transaction);

    /**
     * Maps a list of Transaction entities to a list of TransactionDTOs.
     *
     * @param transactions The source list of Transaction entities to be mapped.
     * @return A list of TransactionDTO objects.
     */
    List<TransactionDTO> toTransactionDTOList(List<Transaction> transactions);
}
