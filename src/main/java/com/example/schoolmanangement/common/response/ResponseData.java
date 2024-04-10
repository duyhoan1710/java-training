package com.example.schoolmanangement.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.util.ObjectUtils;

@Value
public class ResponseData<T> {
    private final T data;
}
