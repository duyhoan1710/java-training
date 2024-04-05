package com.example.schoolmanangement.controller;


import com.example.schoolmanangement.dto.response.TeacherResponse;
import com.example.schoolmanangement.service.TeacherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {

    @Autowired private TeacherService teacherService;

    @GetMapping("/v1/teachers")
    public List<TeacherResponse> getTeachers(@RequestParam("name") final String name) {
        return teacherService.getTeacher(name);
    }
}
