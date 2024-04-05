package com.example.schoolmanangement.controller;


import com.example.schoolmanangement.dto.request.common.PageReq;
import com.example.schoolmanangement.dto.response.StudentResponse;
import com.example.schoolmanangement.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired private StudentService studentService;

    @GetMapping("v1/students")
    public Page<StudentResponse> getStudents(@RequestBody PageReq pageReq) {
        return studentService.getStudents(pageReq).map(StudentResponse::from);
    }

    @GetMapping("v1/classids")
    public List<Long> getClassIds() {
        return studentService.getAllClassIds();
    }
}
