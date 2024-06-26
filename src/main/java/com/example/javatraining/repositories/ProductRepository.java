package com.example.javatraining.repositories;

import com.example.javatraining.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    Page<Product> findByNameLike(String name, Pageable pageable);

    Page<Product> findByNameLikeAndPriceGreaterThanEqualAndPriceLessThanEqual(String name, double priceFrom, double priceTo, Pageable pageable);

    Page<Product> findByPriceLessThanEqual(double priceTo, Pageable pageable);
}
