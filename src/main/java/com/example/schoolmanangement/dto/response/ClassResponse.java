package com.example.schoolmanangement.dto.response;

import com.example.schoolmanangement.entity.Clazz;
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

    public static ClassResponse from(Clazz clazz){
        return ClassResponse.builder()
                .id(clazz.getId())
                .name(clazz.getName())
                .students(clazz.getStudents().stream().map(StudentResponse::from).toList())
                .build();
    }
}
