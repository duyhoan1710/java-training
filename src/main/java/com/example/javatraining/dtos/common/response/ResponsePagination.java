package com.example.javatraining.dtos.common.response;


import lombok.Value;

import java.util.List;

@Value
public class ResponsePagination<T> {
    private final int page;
    private final int limit;
    private final long totalResults;
    private List<T> data;
}
