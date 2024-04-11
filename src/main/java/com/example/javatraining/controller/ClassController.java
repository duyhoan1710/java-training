package com.example.javatraining.controller;


import com.example.javatraining.dto.response.ClassResponse;
import com.example.javatraining.service.ClassService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/class")
public class ClassController {
    private final ClassService classService;

    ClassController (ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/get-all")
    public List<ClassResponse> getClasses() {
        return classService.getAll();
    }

    @GetMapping("/get-all-with-at-least-three-students")
    public List<ClassResponse> findAllWithAtLeastThreeStudents() {
        return classService.findAllWithAtLeastThreeStudents();
    }

    @GetMapping("/get-with-min-number-of-students/{minNumber}")
    public List<ClassResponse> findWithMinNumberOfStudents(
            @PathVariable("minNumber") int minNumber) {
        return classService.findAllWithAtLeastNumberOfStudents(minNumber);
    }
}
