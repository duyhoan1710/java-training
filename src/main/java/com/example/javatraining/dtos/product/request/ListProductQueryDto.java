package com.example.javatraining.dtos.product.request;

import com.example.javatraining.dtos.common.request.PageReq;
import com.example.javatraining.enums.ProductSortBy;
import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class ListProductQueryDto extends PageReq {
    String name;
    double priceFrom;
    double priceTo;
    ProductSortBy sortBy;
    Sort.Direction sortType;
}
