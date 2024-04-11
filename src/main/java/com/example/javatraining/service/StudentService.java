package com.example.javatraining.service;


import com.example.javatraining.common.exception.ErrorCode;
import com.example.javatraining.common.exception.ErrorException;
import com.example.javatraining.common.response.ResponseData;
import com.example.javatraining.common.response.ResponsePagination;
import com.example.javatraining.dto.request.common.PageReq;
import com.example.javatraining.dto.response.StudentResponse;
import com.example.javatraining.entity.Student;
import com.example.javatraining.repository.StudentRepository;
import java.util.List;
import java.util.Optional;

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

    public ResponsePagination<StudentResponse> getStudents(final PageReq pageReq) {
        final Pageable pageable = PageRequest.of(pageReq.getPage() - 1, pageReq.getLimit());

        Page<Student> students = studentRepository.findAll(pageable);

        return new ResponsePagination<>(
            pageReq.getPage(),
            pageReq.getLimit(),
            students.getTotalElements(),
            students.getContent().stream().map(StudentResponse::from).toList()
        );
    }

    public ResponseData<StudentResponse> getStudent(long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isPresent()) {
            return new ResponseData<>(StudentResponse.from(student.get()));
        }

        throw new ErrorException(ErrorCode.USER_NOT_FOUND);
    }
}
