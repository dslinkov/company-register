package com.registry.error;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class LocalError {

    private String exceptionId;
    private String developerMessage;
    private String moreInfo;

    private List<Error> errors;
}
