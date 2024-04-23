package com.example.javatraining.dtos.common.response;


import java.util.List;
import lombok.Value;

@Value
public class ResponsePagination<T> {
    private final int page;
    private final int limit;
    private final long totalResults;
    private List<T> data;
}
