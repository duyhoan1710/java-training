package com.example.javatraining.common.response;

import lombok.Value;

@Value
public class ResponseData<T> {
    private final T data;
}
