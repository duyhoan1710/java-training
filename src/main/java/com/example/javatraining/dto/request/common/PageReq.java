package com.example.javatraining.dto.request.common;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageReq {
    @NotEmpty
    int page;

    @NotEmpty
    int limit;
}
