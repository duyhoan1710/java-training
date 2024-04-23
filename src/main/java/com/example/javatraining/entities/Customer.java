package com.example.javatraining.entities;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customers")
public class Customer extends BaseEntity {
    private String name;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "customers", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "customers", fetch = FetchType.LAZY)
    private List<LineOrder> lineOrders;
}
