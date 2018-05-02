package com.drunkensoftball.api.exception

import org.springframework.web.bind.annotation.ResponseStatus

import org.springframework.http.HttpStatus.CONFLICT

@ResponseStatus(CONFLICT)
class NonUniqueException : BaseDSException {

    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}
