package com.example.javatraining.dtos.report.response;

import com.example.javatraining.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class CustomerReportResponse {
    private Long id;
    private String name;
    private String phone;
    private String address;
    private double totalMoney;

    public static CustomerReportResponse from(final Customer customer, double totalMoney) {
        return CustomerReportResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .totalMoney(totalMoney)
                .build();
    }
}
