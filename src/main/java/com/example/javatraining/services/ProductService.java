package com.example.javatraining.services;

import com.example.javatraining.dtos.product.request.CreateProductDto;

public interface ProductService {
    void createProduct (CreateProductDto payload);
}
