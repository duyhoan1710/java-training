package com.example.javatraining.services;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.product.request.CreateProductDto;
import com.example.javatraining.dtos.product.request.ListProductQueryDto;
import com.example.javatraining.dtos.product.request.UpdateProductDto;
import com.example.javatraining.dtos.product.response.ProductResponse;
import com.example.javatraining.entities.Product;

import java.util.List;

public interface ProductService {
    void createProduct(CreateProductDto payload);

    void updateProduct(long productId, UpdateProductDto payload);

    ResponsePagination<ProductResponse> getProducts(ListProductQueryDto query);

    List<Product> getProductsByIds(List<Long> ids);
}
