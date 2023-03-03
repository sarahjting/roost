package com.sarahjting.roost.common.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// https://stackoverflow.com/a/54849380
// thank u oleksii zghurskyi
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler({ ApiException.class })
    protected ResponseEntity<ApiErrorResponse> handleApiException(ApiException exception) {
        return new ResponseEntity<>(
            new ApiErrorResponse(exception),
            exception.getStatus()
        );
    }
}
