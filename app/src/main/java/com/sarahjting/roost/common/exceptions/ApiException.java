package com.sarahjting.roost.common.exceptions;

import org.springframework.http.HttpStatus;

abstract class ApiException extends RuntimeException {
    private final HttpStatus status;

    public ApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}