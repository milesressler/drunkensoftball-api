package com.drunkensoftball.api.user.controller


import com.drunkensoftball.api.auth.service.AuthenticationService
import com.drunkensoftball.api.user.domain.UserRequest
import com.drunkensoftball.api.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var authenticationService: AuthenticationService

    @RequestMapping(value = [URL_USER], method = [(RequestMethod.POST)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun createUser(@RequestBody userRequest: UserRequest): String {

        val user = userService.createUser(userRequest.username, null, userRequest.email)
        return authenticationService.createToken(user).token
    }

    @RequestMapping(value = ["/health-check"], method = [(RequestMethod.GET)])
    fun healthCheck(): String {
        return "Success"
    }

    companion object {

        private const val URL_USER = "/user"
    }

}