package com.transactionservice.task.service.implementation;

import com.transactionservice.task.domain.Transaction;
import com.transactionservice.task.dto.TransactionDTO;
import com.transactionservice.task.repository.TransactionRepository;
import com.transactionservice.task.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Page<Transaction> getTransactions(Pageable pageable){
        return transactionRepository.findAll(pageable);
    }

    public void saveTransaction(TransactionDTO transactionDTO){
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setCurrency(transactionDTO.getCurrency());
        transaction.setSourceAccountId(transactionDTO.getSourceAccountId());
        transactionRepository.save(transaction);
    }
}
