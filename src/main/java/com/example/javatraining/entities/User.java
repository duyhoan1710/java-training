package com.example.javatraining.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "email")
    String email;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "password")
    String password;
}
