package com.example.schoolmanangement.dto.response;


import java.util.List;
import lombok.Value;

@Value
public class PageResponse<T> {
    private final int page;
    private final long totalResults;
    private List<T> data;
}
