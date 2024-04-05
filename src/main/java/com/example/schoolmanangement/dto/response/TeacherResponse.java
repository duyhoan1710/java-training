package com.example.schoolmanangement.dto.response;


import com.example.schoolmanangement.entity.Teacher;
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
public class TeacherResponse {
    private Long id;
    private String name;
    private int age;
    private boolean isMale;
    private String address;

    public static TeacherResponse from(final Teacher teacher) {
        return TeacherResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .age(teacher.getAge())
                .isMale(teacher.isMale())
                .address(teacher.getAddress())
                .build();
    }
}
