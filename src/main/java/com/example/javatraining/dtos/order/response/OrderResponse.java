package com.example.javatraining.dtos.order.response;

import com.example.javatraining.dtos.customer.response.CustomerResponse;
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
    private double totalMoney;
    private LocalDateTime updatedAt;
    private List<LineOrderResponse> lineOrders;
    private CustomerResponse customer;

    public static OrderResponse from(final Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalMoney(order.getTotalMoney())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public static OrderResponse withLineOrdersFrom(final Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalMoney(order.getTotalMoney())
                .lineOrders(order.getLineOrders().stream().map(LineOrderResponse::from).toList())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public static OrderResponse withCustomerFrom(final Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalMoney(order.getTotalMoney())
                .customer(CustomerResponse.from(order.getCustomer()))
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    public static OrderResponse withCustomerAndLineOrderFrom(final Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .totalMoney(order.getTotalMoney())
                .customer(CustomerResponse.from(order.getCustomer()))
                .lineOrders(order.getLineOrders().stream().map(LineOrderResponse::from).toList())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}