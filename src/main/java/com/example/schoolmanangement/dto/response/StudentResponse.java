package com.example.schoolmanangement.dto.response;


import com.example.schoolmanangement.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private int age;
    private boolean isMale;

    public static StudentResponse from(final Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .age(student.getAge())
                .isMale(student.isMale())
                .build();
    }
}
