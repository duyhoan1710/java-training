package com.example.javatraining.repositories;

import com.example.javatraining.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhone(String phone);

    Optional<Customer> findById(int customerId);

    Page<Customer> findByPhoneLikeOrNameLikeOrAddressLike(String phone, String Name, String Address, Pageable pageable);

    void deleteById(int customerId);
}
