package com.example.javatraining.dtos.order.response;

import com.example.javatraining.dtos.customer.response.CustomerResponse;
import com.example.javatraining.dtos.product.response.ProductResponse;
import com.example.javatraining.entities.LineOrder;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineOrderResponse {
    private long id;

    private CustomerResponse customer;
    private OrderResponse order;
    private ProductResponse product;

    public static LineOrderResponse from(final LineOrder lineOrder) {
        return LineOrderResponse.builder()
                .id(lineOrder.getId())
                .product(ProductResponse.from(lineOrder.getProduct()))
                .build();
    }
}
