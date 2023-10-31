package com.eteration.simplebanking.services;

import com.eteration.simplebanking.base.BaseServiceTest;
import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.mapper.TransactionMapper;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.payload.request.CreateCreditRequest;
import com.eteration.simplebanking.payload.request.CreatePhoneBillPaymentRequest;
import com.eteration.simplebanking.payload.request.CreateWithdrawalRequest;
import com.eteration.simplebanking.payload.request.CreatedAccountRequest;
import com.eteration.simplebanking.payload.response.TransactionResponse;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import java.util.*;
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

    @Test
    public void givenCreateCreditRequest_WhenAccountExists_ThenReturnTransactionResponse() {

        // Given
        String accountNumber = "12345";
        CreateCreditRequest  request = CreateCreditRequest.builder()
                .accountNumber(accountNumber)
                .amount(150.0)
                .build();

        Account account = Account.builder()
                .accountNumber("123-456")
                .owner("John Doe")
                .balance(200.0)
                .transactions(new HashSet<>())
                .build();

        String expectedApprovalCode = "2c9ab7c5-924f-44af-9462-c8ce160fcf11";

        TransactionResponse transactionResponse = TransactionResponse.builder()
                .status("OK")
                .approvalCode(expectedApprovalCode)
                .build();

        UUID mockUUID = UUID.fromString(expectedApprovalCode);
        MockedStatic<UUID> uuidMockedStatic = mockStatic(UUID.class);


        // when
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        uuidMockedStatic.when(UUID::randomUUID).thenReturn(mockUUID);


        // then
        TransactionResponse response = accountService.credit(request);

        assertEquals(transactionResponse.getStatus(), response.getStatus());
        assertEquals(transactionResponse.getApprovalCode(), response.getApprovalCode());

        uuidMockedStatic.close();

        // verify
        verify(accountRepository,times(1)).findByAccountNumber(accountNumber);

    }

    @Test
    public void givenCreateWithdrawalRequest_WhenAccountExists_ThenReturnTransactionResponse() {

        // Given
        String accountNumber = "12345";
        CreateWithdrawalRequest request = CreateWithdrawalRequest.builder()
                .accountNumber(accountNumber)
                .amount(20.0)
                .build();

        Account account = Account.builder()
                .accountNumber("123-456")
                .owner("John Doe")
                .balance(100.0)
                .transactions(new HashSet<>())
                .build();

        String expectedApprovalCode = "2c9ab7c5-924f-44af-9462-c8ce160fcf11";

        TransactionResponse transactionResponse = TransactionResponse.builder()
                .status("OK")
                .approvalCode(expectedApprovalCode)
                .build();

        UUID mockUUID = UUID.fromString(expectedApprovalCode);
        MockedStatic<UUID> uuidMockedStatic = mockStatic(UUID.class);


        // when
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        uuidMockedStatic.when(UUID::randomUUID).thenReturn(mockUUID);


        // then
        TransactionResponse response = accountService.debit(request);

        assertEquals(transactionResponse.getStatus(), response.getStatus());
        assertEquals(transactionResponse.getApprovalCode(), response.getApprovalCode());

        uuidMockedStatic.close();

        // verify
        verify(accountRepository,times(1)).findByAccountNumber(accountNumber);

    }

    @Test
    public void givenCreatePhoneBillPaymentRequest_WhenAccountExists_ThenReturnTransactionResponse() {

        // Given
        String accountNumber = "12345";
        CreatePhoneBillPaymentRequest request = CreatePhoneBillPaymentRequest.builder()
                .accountNumber(accountNumber)
                .amount(20.0)
                .build();

        Account account = Account.builder()
                .accountNumber("123-456")
                .owner("John Doe")
                .balance(100.0)
                .transactions(new HashSet<>())
                .build();

        String expectedApprovalCode = "2c9ab7c5-924f-44af-9462-c8ce160fcf11";

        TransactionResponse transactionResponse = TransactionResponse.builder()
                .status("OK")
                .approvalCode(expectedApprovalCode)
                .build();

        UUID mockUUID = UUID.fromString(expectedApprovalCode);
        MockedStatic<UUID> uuidMockedStatic = mockStatic(UUID.class);


        // when
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        uuidMockedStatic.when(UUID::randomUUID).thenReturn(mockUUID);


        // then
        TransactionResponse response = accountService.payment(request);

        assertEquals(transactionResponse.getStatus(), response.getStatus());
        assertEquals(transactionResponse.getApprovalCode(), response.getApprovalCode());

        uuidMockedStatic.close();

        // verify
        verify(accountRepository,times(1)).findByAccountNumber(accountNumber);

    }

}