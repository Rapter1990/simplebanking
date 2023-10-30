package com.eteration.simplebanking.services;

import com.eteration.simplebanking.base.BaseServiceTest;
import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.mapper.TransactionMapper;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.payload.request.CreatedAccountRequest;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountServiceTest extends BaseServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @Test
    public void givenCreatedAccountRequest_whenCreateAccount_ReturnSavedAccount() {

        // Given
        CreatedAccountRequest request = CreatedAccountRequest.builder()
                .owner("John Doe")
                .build();

        Account account = Account.builder()
                .accountNumber("123-456")
                .owner(request.getOwner())
                .transactions(new HashSet<>())
                .build();

        List<TransactionDTO> transactions = transactionMapper.toTransactionDTOList(
                new ArrayList<>(account.getTransactions())
        );


        // When
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        when(transactionMapper.toTransactionDTOList(any(ArrayList.class))).thenReturn(transactions);

        // then
        AccountDTO accountDTO = accountService.create(request);

        assertEquals(account.getOwner(), accountDTO.getOwner());
        assertEquals(account.getAccountNumber(), accountDTO.getAccountNumber());
        assertEquals(account.getBalance(), accountDTO.getBalance());
        assertEquals(transactions, accountDTO.getTransactionDTOs());

        verify(accountRepository,times(1)).save(any(Account.class));
        verify(transactionMapper, times(2)).toTransactionDTOList(any(ArrayList.class));

    }

    @Test
    public void givenAccountByAccountNumber_WhenAccountExist_ThenAccountDetailInfo() {
        String accountNumber = "123456789";

        Account account = Account.builder()
                .accountNumber("123-456")
                .owner("John Doe")
                .transactions(new HashSet<>())
                .build();

        List<TransactionDTO> transactions = account.getTransactions().stream()
                .map(transactionMapper::toTransactionDTO)
                .collect(Collectors.toList());

        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));

        AccountDTO accountDTO = accountService.getAccountByAccountNumber(accountNumber);

        assertEquals(account.getOwner(), accountDTO.getOwner());
        assertEquals(account.getAccountNumber(), accountDTO.getAccountNumber());
        assertEquals(account.getBalance(), accountDTO.getBalance());
        assertEquals(transactions, accountDTO.getTransactionDTOs());

        verify(accountRepository,times(1)).findByAccountNumber(accountNumber);

    }

    @Test
    public void givenAccountByAccountNumber_WhenAccountDoesNotExist_ThenThrowAccountNotFoundException() {

        // Given
        String accountNumber = "123456789";

        // Mock the behavior of the repository to return an empty optional, simulating an account not found
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.empty());

        // When and Then
        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccountByAccountNumber(accountNumber);
        });

        assertEquals("Account Not Found : " + accountNumber, exception.getMessage());
    }

}