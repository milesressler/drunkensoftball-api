package com.drunkensoftball.api.exception


import org.springframework.web.bind.annotation.ResponseStatus

import org.springframework.http.HttpStatus.BAD_GATEWAY

@ResponseStatus(BAD_GATEWAY)
class BadGatewayException : BaseDSException {

    constructor(message: String) : super(message) {}

    constructor(message: String, cause: Throwable) : super(message, cause) {}
}
