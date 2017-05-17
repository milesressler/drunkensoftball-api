package com.drunkensoftball.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_GATEWAY)
public class BadGatewayException extends RuntimeException {

    public BadGatewayException(final String message){
        super(message);
    }

    public BadGatewayException(final String message, final Throwable cause){
        super(message, cause);
    }
}
