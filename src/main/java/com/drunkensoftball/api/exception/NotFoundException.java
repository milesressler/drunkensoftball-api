package com.drunkensoftball.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message){
        super(message);
    }

    public NotFoundException(final String message, final Throwable cause){
        super(message, cause);
    }
}
