package com.drunkensoftball.api.auth.domain

import org.hibernate.validator.constraints.NotBlank

class AuthenticationRequest {

    @get: NotBlank(message = "{username.required}")
    var user: String = ""

    @get: NotBlank(message = "{password.required}")
    var password: String = ""
}