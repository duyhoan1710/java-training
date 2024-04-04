package com.example.schoolmanangement.repository;

import com.example.schoolmanangement.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

    List<Class> findAll();

    @Query(value = "select * from class where id in (select class_id from student group by (class_id) having count(class_id) > 3)",
            nativeQuery = true)
    List<Class> findClasses();
}
