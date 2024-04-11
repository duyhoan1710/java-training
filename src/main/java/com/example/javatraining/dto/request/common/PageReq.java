package com.example.javatraining.dto.request.common;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PageReq {
    @NotEmpty
    int page;

    @NotEmpty
    int limit;
}
