package com.drunkensoftball.api.user.controller


import com.drunkensoftball.api.auth.domain.AuthenticationEntity
import com.drunkensoftball.api.auth.service.AuthenticationService
import com.drunkensoftball.api.exception.RequestValidationException
import com.drunkensoftball.api.user.domain.UserRequest
import com.drunkensoftball.api.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var authenticationService: AuthenticationService

    @RequestMapping(value = [URL_USER], method = [(RequestMethod.POST)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun createUser(@Valid @RequestBody userRequest: UserRequest,
                   errors: Errors): AuthenticationEntity {
        if (errors.hasErrors()) { throw RequestValidationException(errors) }
        return authenticationService.createNewUserBasicAuthentication(userRequest.username, userRequest.password, userRequest.email, userRequest.firstName, userRequest.lastName)
    }

    @RequestMapping(value = ["/health-check"], method = [(RequestMethod.GET)])
    fun healthCheck(): String {
        return "Success"
    }

    companion object {

        private const val URL_USER = "/user"
    }

}