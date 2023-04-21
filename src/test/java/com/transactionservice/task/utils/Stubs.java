package com.transactionservice.task.utils;

import com.transactionservice.task.domain.Transaction;
import com.transactionservice.task.enums.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.UUID;
public class Stubs {

    public static Page<Transaction> getPaginatedTransactionsStubs(){
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID());
        transaction.setCurrency(Currency.AED);
        transaction.setSourceAccountId(UUID.randomUUID());
        transaction.setAmount(10);
        Transaction transaction2 = new Transaction();
        transaction2.setId(UUID.randomUUID());
        transaction2.setCurrency(Currency.AED);
        transaction2.setSourceAccountId(UUID.randomUUID());
        transaction2.setAmount(10);
        Page<Transaction> paginatedTransactions = new PageImpl<>(Arrays.asList(transaction,transaction2));
        return paginatedTransactions;
    }
}
