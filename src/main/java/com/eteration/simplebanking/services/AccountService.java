package com.eteration.simplebanking.services;


import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.mapper.TransactionMapper;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.payload.request.CreatedAccountRequest;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;


    public AccountDTO create(CreatedAccountRequest request) {

        Account account = Account.builder()
                .owner(request.getOwner())
                .accountNumber(generateUniqueAccountNumber())
                .transactions(new HashSet<>())
                .build();

        Account savedAccount = accountRepository.save(account);

        List<TransactionDTO> transactionDTOs = transactionMapper.toTransactionDTOList(
                new ArrayList<>(savedAccount.getTransactions())
        );

        return AccountDTO.builder()
                .owner(savedAccount.getOwner())
                .accountNumber(savedAccount.getAccountNumber())
                .createdDateTime(savedAccount.getCreatedDateTime())
                .balance(savedAccount.getBalance())
                .transactionDTOs(transactionDTOs)
                .build();
    }

    public AccountDTO getAccountByAccountNumber(String accountNumber) {
        
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found : " + accountNumber));

        List<TransactionDTO> transactionDTOS = account.getTransactions().stream()
                        .map(transactionMapper::toTransactionDTO)
                        .collect(Collectors.toList());

        return AccountDTO.builder()
                .owner(account.getOwner())
                .accountNumber(account.getAccountNumber())
                .createdDateTime(account.getCreatedDateTime())
                .balance(account.getBalance())
                .transactionDTOs(transactionDTOS)
                .build();
    }


    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            String randomNumber = generateRandomNumber();
            accountNumber = randomNumber.substring(0, 3) + "-" + randomNumber.substring(3);
        } while (!isAccountNumberUnique(accountNumber));
        return accountNumber;
    }

    private String generateRandomNumber() {
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        int randomNumber = random.nextInt((max - min) + 1) + min;
        return String.valueOf(randomNumber);
    }

    private boolean isAccountNumberUnique(String accountNumber) {

        Optional<Account> existingAccount = accountRepository.findByAccountNumber(accountNumber);
        return existingAccount.isEmpty();
    }
}
