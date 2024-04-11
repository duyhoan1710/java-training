package com.example.javatraining.dto.request.common;


import lombok.Value;

@Value
public class PageReq {
    private final int page;
    private final int limit;
}
