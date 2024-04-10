package com.example.schoolmanangement.service;


import com.example.schoolmanangement.common.exception.ErrorCode;
import com.example.schoolmanangement.common.exception.ErrorException;
import com.example.schoolmanangement.common.response.ResponseData;
import com.example.schoolmanangement.common.response.ResponsePagination;
import com.example.schoolmanangement.dto.request.common.PageReq;
import com.example.schoolmanangement.dto.response.StudentResponse;
import com.example.schoolmanangement.entity.Student;
import com.example.schoolmanangement.repository.StudentRepository;
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

        throw new ErrorException(ErrorCode.STUDENT_NOT_FOUND);
    }
}
