package com.drunkensoftball.api.exception

import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.validation.Errors

@ResponseStatus(BAD_REQUEST)
class RequestValidationException : BaseDSException {

    constructor(errors: Errors) : super("Invalid Parameters: " + errors.allErrors.map { it.defaultMessage }.joinToString()) { }

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}
