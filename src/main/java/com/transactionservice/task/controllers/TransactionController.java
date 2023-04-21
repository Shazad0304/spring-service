package com.transactionservice.task.controllers;

import com.transactionservice.task.domain.Transaction;
import com.transactionservice.task.dto.TransactionDTO;
import com.transactionservice.task.models.SuccessResponse;
import com.transactionservice.task.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    private Page<Transaction> getTransactions(Pageable pageable){
        return transactionService.getTransactions(pageable);
    }

    @PostMapping("/save")
    private ResponseEntity<SuccessResponse> saveTransaction(
            @RequestBody @Valid TransactionDTO transactionDTO){
        transactionService.saveTransaction(transactionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("Transaction Saved"));
    }

}
