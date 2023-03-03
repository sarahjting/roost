package com.sarahjting.roost.common.exceptions;

import org.springframework.http.HttpStatus;

public class NoDefaultStorageException extends ApiException {
    public NoDefaultStorageException() {
        super(HttpStatus.BAD_REQUEST, "No default storage has been set. Please check your storage settings to proceed.");
    }
}
