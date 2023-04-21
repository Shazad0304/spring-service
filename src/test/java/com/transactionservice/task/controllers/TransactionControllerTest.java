package com.transactionservice.task.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactionservice.task.dto.TransactionDTO;
import com.transactionservice.task.enums.Currency;
import com.transactionservice.task.service.TransactionService;
import com.transactionservice.task.utils.Stubs;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService transactionService;


    @Test
    public void invalidRouteTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/transaction/test")
                        .content(asJsonString(new TransactionDTO(10, UUID.randomUUID(), Currency.AED)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getTransactionsTest() throws Exception {
        when(transactionService.getTransactions(Mockito.any(PageRequest.class)))
                .thenReturn(Stubs.getPaginatedTransactionsStubs());
        mvc.perform(MockMvcRequestBuilders
                        .get("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists());
    }

    @Test
    public void saveTransactionTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/transaction/save")
                        .content(asJsonString(new TransactionDTO(10, UUID.randomUUID(), Currency.AED)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());
    }

    @Test
    public void saveTransactionValidationTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/transaction/save")
                        .content(asJsonString(new TransactionDTO(0, null, null)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sourceAccountId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sourceAccountId").
                        value("Invalid Account Id: Account Id is NULL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").
                        value("amount must be greater than 0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").
                        value("Invalid currency: currency is NULL"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
