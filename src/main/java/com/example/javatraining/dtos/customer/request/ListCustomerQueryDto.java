package com.example.javatraining.dtos.customer.request;

import com.example.javatraining.dtos.common.request.PageReq;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ListCustomerQueryDto extends PageReq {
    @NotEmpty
    String phone;

    @NotEmpty
    String name;

    @NotEmpty
    String address;

    public ListCustomerQueryDto(int page, int limit, String phone, String name, String address) {
        super(page, limit);
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}
