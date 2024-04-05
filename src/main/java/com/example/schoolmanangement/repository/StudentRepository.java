package com.example.schoolmanangement.repository;


import com.example.schoolmanangement.entity.Student;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAll();

    @Query(value = "select distinct s.clazz.id from Student s")
    List<Long> getAllClassIds();

    Page<Student> findAll(Pageable pageable);
}
