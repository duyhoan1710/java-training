package com.example.javatraining.dtos.report.response;

import com.example.javatraining.entities.Inventory;
import com.example.javatraining.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Builder
public class ProductInventoryReportResponse {
    private Long id;
    private String name;
    private double price;
    private Inventory inventory;

    public static ProductInventoryReportResponse from(final Product product) {
        return ProductInventoryReportResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .inventory(product.getInventory())
                .build();
    }
}
