package com.example.javatraining.controllers;

import com.example.javatraining.dtos.common.response.ResponsePagination;
import com.example.javatraining.dtos.product.request.CreateProductDto;
import com.example.javatraining.dtos.product.request.ListProductQueryDto;
import com.example.javatraining.dtos.product.request.UpdateProductDto;
import com.example.javatraining.dtos.product.response.ProductResponse;
import com.example.javatraining.enums.ProductSortBy;
import com.example.javatraining.services.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('OPERATOR')")
    @PostMapping("products")
    void createProduct(@RequestBody CreateProductDto payload) {
        this.productService.createProduct(payload);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("products/{productId}")
    void updateProduct(@PathVariable long productId, @Valid @RequestBody UpdateProductDto payload) {
        this.productService.updateProduct(productId, payload);
    }

    @GetMapping("products")
    ResponsePagination<ProductResponse> getProducts(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int limit,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false) double priceFrom,
            @RequestParam(required = false) double priceTo,
            @RequestParam(required = true) ProductSortBy sortBy,
            @RequestParam(required = true) Sort.Direction sortType
    ) {
        ListProductQueryDto listProductQueryDto = new ListProductQueryDto();
        listProductQueryDto.setPage(page);
        listProductQueryDto.setLimit(limit);
        listProductQueryDto.setName(name);
        listProductQueryDto.setPriceFrom(priceFrom);
        listProductQueryDto.setPriceTo(priceTo);
        listProductQueryDto.setSortBy(sortBy);
        listProductQueryDto.setSortType(sortType);

        return productService.getProducts(listProductQueryDto);
    }
}
