package com.transactionservice.task.service;
import com.transactionservice.task.domain.Transaction;
import com.transactionservice.task.dto.TransactionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    Page<Transaction> getTransactions(Pageable pageable);

    void saveTransaction(TransactionDTO transaction);
}
