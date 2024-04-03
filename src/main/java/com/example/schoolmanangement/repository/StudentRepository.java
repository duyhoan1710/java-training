package com.example.schoolmanangement.repository;

import com.example.schoolmanangement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAll();

    @Query(value = "select class_id from student group by (class_id) having count(class_id) > 3",
            nativeQuery = true)
    List<Long> findClassIds();
}
