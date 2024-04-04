package com.example.schoolmanangement.controller;


import com.example.schoolmanangement.dto.response.ClassResponse;
import com.example.schoolmanangement.service.ClassService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassController {

    @Autowired private ClassService classService;

    @GetMapping("/v1/class")
    public List<ClassResponse> getClasses() {
        return classService.getAll();
    }

    @GetMapping("/v1/classes")
    public List<ClassResponse> getClassesV2() {
        return classService.getClasses();
    }
}
