package com.transactionservice.task.domain;

import com.transactionservice.task.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID Id;
    private double amount;

    @Column(nullable = false)
    private UUID sourceAccountId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;
}
