package com.example.schoolmanangement.service;

import com.example.schoolmanangement.dto.response.ClassResponse;
import com.example.schoolmanangement.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Transactional
    public List<ClassResponse> getAll(){
        return classRepository.findAll().stream().map(ClassResponse::from).toList();
    }

    public List<ClassResponse> getClasses(){
        return classRepository.findClasses().stream().map(ClassResponse::from).toList();
    }
}
