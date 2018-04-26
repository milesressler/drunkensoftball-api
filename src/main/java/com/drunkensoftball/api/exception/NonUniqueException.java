package com.drunkensoftball.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;

@ResponseStatus(CONFLICT)
public class NonUniqueException extends RuntimeException {

    public NonUniqueException(final String message){
        super(message);
    }

    public NonUniqueException(final String message, final Throwable cause){
        super(message, cause);
    }
}
