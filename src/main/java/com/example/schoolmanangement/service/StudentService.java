package com.example.schoolmanangement.service;

import com.example.schoolmanangement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Long> findClassIds(){
        return studentRepository.findClassIds();
    }

}
