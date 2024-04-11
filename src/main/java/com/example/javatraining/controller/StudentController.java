package com.example.javatraining.controller;


import com.example.javatraining.common.response.ResponseData;
import com.example.javatraining.common.response.ResponsePagination;
import com.example.javatraining.dto.request.common.PageReq;
import com.example.javatraining.dto.response.StudentResponse;
import com.example.javatraining.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired private StudentService studentService;

    @GetMapping("v1/students")
    public ResponsePagination<StudentResponse> getStudents(@RequestParam int page, @RequestParam int limit) {
        return studentService.getStudents(new PageReq(page, limit));
    }

    @GetMapping("v1/students/{id}")
    public ResponseData<StudentResponse> getStudent(@PathVariable long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("v1/classids")
    public List<Long> getClassIds() {
        return studentService.getAllClassIds();
    }
}
