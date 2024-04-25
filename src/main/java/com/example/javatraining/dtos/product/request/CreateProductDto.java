package com.example.javatraining.dtos.product.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateProductDto {
    @NotEmpty
    String name;

    @NotNull
    @Min(0)
    double price;

    @NotNull
    @Min(1)
    int quantity;
}
