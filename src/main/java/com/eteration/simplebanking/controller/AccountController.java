package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.mapper.AccountMapper;
import com.eteration.simplebanking.payload.request.CreatedAccountRequest;
import com.eteration.simplebanking.payload.response.CreatedAccountResponse;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final AccountMapper accountMapper;

    @PostMapping(value = "/create")
    public ResponseEntity<CreatedAccountResponse> createAccount(@RequestBody CreatedAccountRequest request) {

        AccountDTO accountDTO = accountService.create(request);
        return ResponseEntity.ok(accountMapper.toAccountResponse(accountDTO));
    }

}