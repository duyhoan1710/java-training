package com.example.schoolmanangement.controller;


import com.example.schoolmanangement.entity.Student;
import com.example.schoolmanangement.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired private StudentRepository studentRepository;

    @GetMapping("v1/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("v1/classids")
    public List<Long> getClassIds() {
        return studentRepository.findClassIds();
    }
}
