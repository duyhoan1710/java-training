package com.example.javatraining.dtos.report.request;

import com.example.javatraining.dtos.common.request.PageReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductInventoryReportDto extends PageReq {
    private int inventory;
}
