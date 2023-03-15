package com.geekster.expensetracker.dto;
import lombok.Data;

@Data
public class ExpenseDto {
    private String title;
    private String description;
    private String price;
    private String userId;
}
