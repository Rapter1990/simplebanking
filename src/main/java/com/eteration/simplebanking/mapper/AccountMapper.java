package com.eteration.simplebanking.mapper;

import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.payload.response.CreatedAccountResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    CreatedAccountResponse toAccountResponse(AccountDTO accountDTO);
}
