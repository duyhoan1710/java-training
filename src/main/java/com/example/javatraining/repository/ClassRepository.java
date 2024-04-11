package com.example.javatraining.repository;


import com.example.javatraining.entity.Class;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

    List<Class> findAll();

    @Query(
            value =
                    "select * from class where id in (select class_id from student group by"
                            + " (class_id) having count(class_id) > 3)",
            nativeQuery = true)
    List<Class> findAllWithAtLeastThreeStudents();

    @Query(
            value =
                    "select c from Class c where c.id in (select s.clazz.id from Student s group by"
                            + " s.clazz.id having count(s.clazz.id) > :minNumberOfStudents)")
    List<Class> findAllWithAtLeastNumberOfStudents(
            @Param("minNumberOfStudents") int minNumberOfStudents);
}
