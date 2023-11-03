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

/**
 * Controller class for managing account-related operations in the Simple Banking App API.
 */
@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final AccountMapper accountMapper;

    /**
     * Creates a new account based on the provided request data.
     *
     * @param request The request object containing information for creating an account.
     * @return A ResponseEntity with a CreatedAccountResponse as the response body.
     */
    @PostMapping(value = "/create")
    public ResponseEntity<CreatedAccountResponse> createAccount(@RequestBody CreatedAccountRequest request) {

        AccountDTO accountDTO = accountService.create(request);
        return ResponseEntity.ok(accountMapper.toAccountResponse(accountDTO));
    }

    /**
     * Retrieves account details by the account number.
     *
     * @param accountNumber The account number to retrieve account details for.
     * @return A ResponseEntity with AccountDetailInfo as the response body.
     */
    @GetMapping("/account-number/{accountNumber}")
    public ResponseEntity<AccountDetailInfo> getAccountDetails(@PathVariable String accountNumber){
        AccountDTO accountDTO = accountService.getAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(accountMapper.toAccountDetailInfo(accountDTO));
    }

    /**
     * Performs a credit operation on the account.
     *
     * @param createCreditRequest The request object for crediting the account.
     * @return A ResponseEntity with TransactionResponse as the response body.
     */
    @PostMapping(value = "/credit")
    public ResponseEntity<TransactionResponse> credit(@RequestBody CreateCreditRequest createCreditRequest) {

        TransactionResponse credit = accountService.credit(createCreditRequest);
        return ResponseEntity.ok(credit);
    }

    /**
     * Performs a debit operation on the account.
     *
     * @param createWithdrawalRequest The request object for debiting the account.
     * @return A ResponseEntity with TransactionResponse as the response body.
     */
    @PostMapping(value = "/debit")
    public ResponseEntity<TransactionResponse> debit(@RequestBody CreateWithdrawalRequest createWithdrawalRequest) {

        TransactionResponse debit = accountService.debit(createWithdrawalRequest);
        return ResponseEntity.ok(debit);
    }

    /**
     * Performs a payment operation, such as a phone bill payment, from the account.
     *
     * @param createPhoneBillPaymentRequest The request object for making a payment from the account.
     * @return A ResponseEntity with TransactionResponse as the response body.
     */
    @PostMapping(value = "/payment")
    public ResponseEntity<TransactionResponse> payment(@RequestBody CreatePhoneBillPaymentRequest createPhoneBillPaymentRequest) {

        TransactionResponse payment = accountService.payment(createPhoneBillPaymentRequest);
        return ResponseEntity.ok(payment);
    }

}