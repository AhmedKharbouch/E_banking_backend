package com.bank.ebankingbackend.dtos;

import com.bank.ebankingbackend.entities.BankAccount;
import com.bank.ebankingbackend.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private  String description;
}
