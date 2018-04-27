package com.drunkensoftball.api.user.domain

import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.Size


class UserRequest {

    @get: NotBlank(message = "{username.required}")
    var username: String = ""

    @get: NotBlank(message = "{password.required}")
    @get: Size(min=6, message = "{password.size}")
    var password: String = ""

    @get: NotBlank(message = "{email.required}")
    @get: Email(message = "{email.invalid}")
    var email: String = ""

    var firstName: String? = null
    var lastName: String? = null
}
