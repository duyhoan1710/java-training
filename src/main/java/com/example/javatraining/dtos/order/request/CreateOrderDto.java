package com.example.javatraining.dtos.order.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateOrderDto {
    @NotEmpty
    long customerId;

    @NotEmpty
    List<ProductDto> products;
}
