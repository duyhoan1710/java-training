package com.example.javatraining.dtos.product.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateProductDto {
    @NotEmpty
    String name;

    @NotEmpty
    int quantity;

    @NotEmpty
    double price;

}
