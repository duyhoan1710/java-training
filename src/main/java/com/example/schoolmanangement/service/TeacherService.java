package com.example.schoolmanangement.service;


import com.example.schoolmanangement.dto.response.TeacherResponse;
import com.example.schoolmanangement.repository.TeacherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired private TeacherRepository teacherRepository;

    public List<TeacherResponse> getTeacher(final String name) {
        return teacherRepository.findByName(name).stream().map(TeacherResponse::from).toList();
    }
}
