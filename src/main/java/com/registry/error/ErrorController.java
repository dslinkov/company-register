package com.registry.error;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public HttpEntity<LocalError> handleConstraintViolation(ConstraintViolationException ex) {

        LocalError localError = new LocalError();
        List<Error> errors = new ArrayList<>();

        // TODO: Get info from constraint violation exception and populate errro
        // objects

        return handleException(ex, HttpStatus.UNPROCESSABLE_ENTITY);

    }

    private HttpEntity<LocalError> handleException(Exception ex, HttpStatus httpStatus) {

        LocalError localError = new LocalError();

        List<Error> errors = new ArrayList<>();

        Error error = new Error();
        error.setErrorType(StringUtils.trimToEmpty(StringUtils.substringBefore(ex.getClass().getSimpleName(), "Exception")));
        error.setMessage(ex.getMessage());
        error.setProperty("N/A");
        error.setRejectedValue("N/A");

        errors.add(error);

        localError.setErrors(errors);

        setAndLogDnaErrorMessage(ex, localError);

        return new ResponseEntity<>(localError, httpStatus);
    }

    private void setAndLogDnaErrorMessage(Exception ex, final LocalError dnaError) {

        UUID exceptionId = UUID.randomUUID();

        StringBuilder message = new StringBuilder().append(ex.getClass().getName()).append(" - Message: \"").append(ex.getMessage()).append("\"")
                .append(" When inquiring about the error use this exception Id: ").append(exceptionId);

        dnaError.setDeveloperMessage(message.toString());

        StringBuilder errMessage = new StringBuilder();

        errMessage.append(ex.getClass().getName()).append(": ").append(exceptionId).append("\n")
                .append(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()));

        dnaError.setExceptionId(exceptionId.toString());

        log.error(errMessage.toString(), ex);
    }

}
