package com.example.javatraining.repositories;

import com.example.javatraining.dtos.order.response.CustomerReport;
import com.example.javatraining.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = "SELECT sum(total_money) as revenue FROM orders o WHERE o.created_at >= :from_date and o.created_at <= :to_date")
    Optional<Double> getRevenueReport(@Param("from_date") LocalDateTime fromDate, @Param("to_date") LocalDateTime toDate);

    @Query(value = """
                SELECT o.customer_id as customerId, SUM(total_money) totalMoney
                FROM orders o
                WHERE o.created_at >= :from_date and o.created_at <= :to_date
                GROUP BY o.customer_id
                ORDER BY totalMoney DESC
                LIMIT 1 OFFSET 0
            """, nativeQuery = true)
    Optional<CustomerReport> getCustomerReport(@Param("from_date") LocalDateTime fromDate, @Param("to_date") LocalDateTime toDate);
}
