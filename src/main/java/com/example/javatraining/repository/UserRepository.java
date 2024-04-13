package com.example.javatraining.repository;

import com.example.javatraining.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByFullNameLike(String fullName, Pageable pageable);

    Optional<User> findById(long userId);

    User save(User user);
}
