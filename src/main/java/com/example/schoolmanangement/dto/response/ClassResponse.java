package com.example.schoolmanangement.dto.response;

import com.example.schoolmanangement.entity.Class;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassResponse {
    private Long id;
    private String name;
    private List<StudentResponse> students;

    public static ClassResponse from(Class aClass){
        return ClassResponse.builder()
                .id(aClass.getId())
                .name(aClass.getName())
                .students(aClass.getStudents().stream().map(StudentResponse::from).toList())
                .build();
    }
}
