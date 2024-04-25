package com.example.javatraining.dtos.product.request;

import com.example.javatraining.dtos.common.request.PageReq;
import com.example.javatraining.enums.ProductSortBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListProductQueryDto extends PageReq {
    String name;
    double priceFrom;
    double priceTo;
    ProductSortBy sortBy;
    Sort.Direction sortType;
}
