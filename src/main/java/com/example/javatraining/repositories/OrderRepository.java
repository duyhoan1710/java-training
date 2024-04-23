package com.example.javatraining.repositories;

import com.example.javatraining.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
OrderRepository extends JpaRepository<Order, Long> {
}
