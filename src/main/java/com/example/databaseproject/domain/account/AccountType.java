package com.example.databaseproject.domain.account;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountType {
    private Long id;
    private String description;
    private double interestRate;
    private String name;

}
