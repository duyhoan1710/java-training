package com.example.schoolmanangement.service;


import com.example.schoolmanangement.dto.request.common.PageReq;
import com.example.schoolmanangement.entity.Student;
import com.example.schoolmanangement.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired private StudentRepository studentRepository;

    public List<Long> getAllClassIds() {
        return studentRepository.getAllClassIds();
    }

    public Page<Student> getStudents(final PageReq pageReq) {
        final Pageable pageable = PageRequest.of(pageReq.getPage() - 1, pageReq.getLimit());
        return studentRepository.findAll(pageable);
    }
}
