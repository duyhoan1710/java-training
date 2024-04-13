package com.example.javatraining.dto.user.request;

import com.example.javatraining.dto.request.common.PageReq;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ListUserQueryDto extends PageReq {
    @NotEmpty
    String fullName;

    public ListUserQueryDto (int page, int limit, String fullName) {
        super(page, limit);
        this.fullName = fullName;
    }
}
