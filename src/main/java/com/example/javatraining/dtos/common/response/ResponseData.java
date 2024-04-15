package com.example.javatraining.dtos.common.response;


import lombok.Value;

@Value
public class ResponseData<T> {
    private final T data;
}
