package com.geekster.expensetracker.dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String phoneNumber;
}
