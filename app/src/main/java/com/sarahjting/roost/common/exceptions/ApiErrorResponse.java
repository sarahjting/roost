package com.sarahjting.roost.common.exceptions;

import lombok.Getter;

@Getter
class ApiErrorResponse {
    private int status;
    private String message;

    public ApiErrorResponse(ApiException exception) {
        this.status = exception.getStatus().value();
        this.message = exception.getMessage();
    }
}
