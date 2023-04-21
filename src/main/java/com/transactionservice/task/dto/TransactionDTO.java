package com.transactionservice.task.dto;

import com.transactionservice.task.enums.Currency;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDTO {

    @Min(value = 1,message = "amount must be greater than 0")
    private double amount;

    @NotNull(message = "Invalid Account Id: Account Id is NULL")
    private UUID sourceAccountId;

    @NotNull(message = "Invalid currency: currency is NULL")
    private Currency currency;
}
