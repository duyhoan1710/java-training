package com.example.javatraining.dto.response;


import com.example.javatraining.entity.Class;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassResponse {
    private Long id;
    private String name;
    private List<StudentResponse> students;

    public static ClassResponse from(@NonNull final Class aClass) {
        return ClassResponse.builder()
                .id(aClass.getId())
                .name(aClass.getName())
                .students(aClass.getStudents().stream().map(StudentResponse::from).toList())
                .build();
    }
}
