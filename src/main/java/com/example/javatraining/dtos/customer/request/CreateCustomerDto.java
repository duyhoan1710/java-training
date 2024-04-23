package com.example.javatraining.dtos.customer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateCustomerDto {
    private String name;
    private String phone;
    private String address;
}
