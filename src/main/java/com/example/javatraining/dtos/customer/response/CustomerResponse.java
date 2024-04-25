package com.example.javatraining.dtos.customer.response;

import com.example.javatraining.dtos.order.response.OrderResponse;
import com.example.javatraining.entities.Customer;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private List<OrderResponse> orders;

    public static CustomerResponse from(final Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .build();
    }

    public static CustomerResponse withOrdersFrom(final Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .orders(customer.getOrders().stream().map(OrderResponse::from).toList())
                .build();
    }

    public static CustomerResponse withOrdersAndLineOrdersFrom(final Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .orders(customer.getOrders().stream().map(OrderResponse::withLineOrdersFrom).toList())
                .build();
    }
}
