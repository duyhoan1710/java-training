package com.example.schoolmanangement.service;


import com.example.schoolmanangement.dto.response.ClassResponse;
import com.example.schoolmanangement.repository.ClassRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClassService {

    @Autowired private ClassRepository classRepository;

    @Transactional
    public List<ClassResponse> getAll() {
        return classRepository.findAll().stream().map(ClassResponse::from).toList();
    }

    @Transactional
    public List<ClassResponse> findAllWithAtLeastThreeStudents() {
        return classRepository.findAllWithAtLeastThreeStudents().stream()
                .map(ClassResponse::from)
                .toList();
    }

    @Transactional
    public List<ClassResponse> findAllWithAtLeastNumberOfStudents(int minNumber) {
        return classRepository.findAllWithAtLeastNumberOfStudents(minNumber).stream()
                .map(ClassResponse::from)
                .toList();
    }
}
