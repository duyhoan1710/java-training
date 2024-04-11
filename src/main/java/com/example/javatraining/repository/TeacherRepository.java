package com.example.javatraining.repository;


import com.example.javatraining.entity.Teacher;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByName(String name);

    List<Teacher> findByNameAndAge(String name, int age);

    List<Teacher> findByNameAndAgeLessThan(String name, int age);

    List<Teacher> findByAgeBetween(int lowerAge, int higherAge);

    List<Teacher> findByNameLike(String name);

    List<Teacher> findByNameStartingWith(String name);

    List<Teacher> findByNameContaining(String name);

    List<Teacher> findByAddressOrderByAgeDesc(String address);

    List<Teacher> findByIsMaleIsTrue();
}
