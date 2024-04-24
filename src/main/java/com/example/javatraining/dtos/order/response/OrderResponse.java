package com.example.javatraining.dtos.order.response;

import com.example.javatraining.entities.Customer;
import com.example.javatraining.entities.LineOrder;
import com.example.javatraining.entities.Order;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private long id;
    private Customer customer;
    private LocalDateTime updatedAt;
    private List<LineOrder> lineOrders;

    public static OrderResponse from(final Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customer(order.getCustomer())
                .lineOrders(order.getLineOrders())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}