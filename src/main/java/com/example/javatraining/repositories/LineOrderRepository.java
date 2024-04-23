package com.example.javatraining.repositories;

import com.example.javatraining.entities.LineOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineOrderRepository extends JpaRepository<LineOrder, Long> {
}
