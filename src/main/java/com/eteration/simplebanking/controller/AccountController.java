package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.mapper.AccountMapper;
import com.eteration.simplebanking.payload.request.CreateCreditRequest;
import com.eteration.simplebanking.payload.request.CreatePhoneBillPaymentRequest;
import com.eteration.simplebanking.payload.request.CreateWithdrawalRequest;
import com.eteration.simplebanking.payload.request.CreatedAccountRequest;
import com.eteration.simplebanking.payload.response.AccountDetailInfo;
import com.eteration.simplebanking.payload.response.CreatedAccountResponse;
import com.eteration.simplebanking.payload.response.TransactionResponse;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/account-number/{accountNumber}")
    public ResponseEntity<AccountDetailInfo> getAccountDetails(@PathVariable String accountNumber){
        AccountDTO accountDTO = accountService.getAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(accountMapper.toAccountDetailInfo(accountDTO));
    }

    @PostMapping(value = "/credit")
    public ResponseEntity<TransactionResponse> credit(@RequestBody CreateCreditRequest createCreditRequest) {

        TransactionResponse credit = accountService.credit(createCreditRequest);
        return ResponseEntity.ok(credit);
    }

    @PostMapping(value = "/debit")
    public ResponseEntity<TransactionResponse> debit(@RequestBody CreateWithdrawalRequest createWithdrawalRequest) {

        TransactionResponse debit = accountService.debit(createWithdrawalRequest);
        return ResponseEntity.ok(debit);
    }

    @PostMapping(value = "/payment")
    public ResponseEntity<TransactionResponse> payment(@RequestBody CreatePhoneBillPaymentRequest createPhoneBillPaymentRequest) {

        TransactionResponse payment = accountService.payment(createPhoneBillPaymentRequest);
        return ResponseEntity.ok(payment);
    }

}