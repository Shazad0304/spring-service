package com.transactionservice.task.service;

import com.transactionservice.task.domain.Transaction;
import com.transactionservice.task.dto.TransactionDTO;
import com.transactionservice.task.enums.Currency;
import com.transactionservice.task.repository.TransactionRepository;
import com.transactionservice.task.service.implementation.TransactionServiceImpl;
import com.transactionservice.task.utils.Stubs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepository transactionRepository;


    @Test
    public void getTransactionsTest(){
        Pageable pageable = PageRequest.of(0,10);
        when(transactionRepository.findAll(pageable))
                .thenReturn(Stubs.getPaginatedTransactionsStubs());
        Page<Transaction> paginatedResponse = transactionService
                .getTransactions(pageable);
        assertFalse("Content should be there",paginatedResponse.getContent().isEmpty());
        assertTrue("Should be two transactions",
                paginatedResponse.getContent().size() == 2);
    }

    @Test
    public void saveTransactionTest(){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setCurrency(Currency.AED);
        transactionDTO.setSourceAccountId(UUID.randomUUID());
        transactionDTO.setAmount(10);
        transactionService.saveTransaction(transactionDTO);
        ArgumentCaptor<Transaction> argument = ArgumentCaptor.forClass(Transaction.class);
        Mockito.verify(transactionRepository,Mockito.timeout(1)).save(Mockito.any());
        Mockito.verify(transactionRepository).save(argument.capture());
        assertEquals("Id should be same", transactionDTO.getSourceAccountId(),
                argument.getValue().getSourceAccountId());
        assertEquals("Amount should be same", transactionDTO.getAmount(),
                argument.getValue().getAmount());
        assertEquals("Currency should be same", transactionDTO.getCurrency(),
                argument.getValue().getCurrency());

    }
}
