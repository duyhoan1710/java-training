package com.example.javatraining.dtos.product.response;


import com.example.javatraining.entities.Inventory;
import com.example.javatraining.entities.Product;
import com.example.javatraining.entities.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private double price;
    private Inventory inventory;

    public static ProductResponse from(final Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .inventory(product.getInventory())
                .build();
    }
}
