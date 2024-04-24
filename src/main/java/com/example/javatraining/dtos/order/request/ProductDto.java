package com.example.javatraining.dtos.order.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductDto {
    @NotEmpty
    long id;

    @NotEmpty
    int quantity;
}
