package com.example.javatraining.dtos.user.request;


import com.example.javatraining.dtos.common.request.PageReq;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ListUserQueryDto extends PageReq {
    @NotEmpty
    String name;

    public ListUserQueryDto(int page, int limit, String name) {
        super(page, limit);
        this.name = name;
    }
}
