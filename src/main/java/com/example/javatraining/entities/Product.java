package com.example.javatraining.entities;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "products")
public class Product extends BaseEntity {
    private String name;
    private Double price;

    @OneToOne(mappedBy = "products", fetch = FetchType.LAZY)
    private Inventory inventory;

    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private List<LineOrder> lineOrders;
}
