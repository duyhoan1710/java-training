package com.example.javatraining.dto.user.request;

import com.example.javatraining.dto.request.common.PageReq;
import jakarta.validation.constraints.NotEmpty;
import lombok.Value;

@Value
public class ListUserQueryDto extends PageReq {
    @NotEmpty
    String fullName;
}
