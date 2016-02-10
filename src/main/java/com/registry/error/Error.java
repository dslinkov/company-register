package com.registry.error;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class Error {

    private String errorType;
    private String property;
    private String message;
    private String rejectedValue;

}