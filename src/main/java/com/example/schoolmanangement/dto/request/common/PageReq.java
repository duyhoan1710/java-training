package com.example.schoolmanangement.dto.request.common;


import lombok.Value;

@Value
public class PageReq {
    private final int page;
    private final int limit;
}
