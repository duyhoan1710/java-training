package com.example.javatraining.dtos.customer.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateCustomerDto {
    @NotEmpty
    private String name;

    @NotEmpty
    @Pattern(regexp = "^(0|\\+84)\\d{9,10}$", message = "Phone is invalid")
    private String phone;

    @NotEmpty
    private String address;
}
