package com.example.javatraining.repositories;

import com.example.javatraining.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByNameLike(String name, Pageable pageable);

    Optional<User> findById(long userId);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrPhone(String email, String phone);
}
