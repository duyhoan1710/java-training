package com.example.javatraining.dtos.product.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateProductDto {
    @NotEmpty
    String name;

    @NotEmpty
    double price;

    @NotEmpty
    int quantity;
}
