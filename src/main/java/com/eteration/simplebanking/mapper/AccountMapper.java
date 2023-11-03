package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.payload.response.AccountDetailInfo;
import com.eteration.simplebanking.payload.response.CreatedAccountResponse;
import org.mapstruct.Mapper;

/**
 * Mapper interface for mapping between AccountDTO and response objects in the Simple Banking App.
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    /**
     * Maps an AccountDTO to a CreatedAccountResponse object.
     *
     * @param accountDTO The source AccountDTO to be mapped.
     * @return A CreatedAccountResponse object.
     */
    CreatedAccountResponse toAccountResponse(AccountDTO accountDTO);

    /**
     * Maps an AccountDTO to an AccountDetailInfo object.
     *
     * @param accountDTO The source AccountDTO to be mapped.
     * @return An AccountDetailInfo object.
     */
    AccountDetailInfo toAccountDetailInfo(AccountDTO accountDTO);

}
