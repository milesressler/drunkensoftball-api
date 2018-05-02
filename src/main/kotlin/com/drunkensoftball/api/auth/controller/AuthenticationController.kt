package com.drunkensoftball.api.auth.controller


import com.drunkensoftball.api.auth.domain.AuthenticationEntity
import com.drunkensoftball.api.auth.domain.AuthenticationRequest
import com.drunkensoftball.api.auth.service.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.POST

@RestController
class AuthenticationController {

    @Autowired
    lateinit var authenticationService: AuthenticationService

    @RequestMapping(value = [URL_GOOGLE], method = [POST])
    fun googleAuth(@RequestHeader(value = "X-Google-Authorization") idTokenString: String): AuthenticationEntity {
        return authenticationService.googleAuthentication(idTokenString)
    }

    @RequestMapping(value = [URL_BASIC], method = [POST])
    fun basicAuth(@RequestBody request: AuthenticationRequest): AuthenticationEntity? {
        return authenticationService.loginWithBasicAuthentication(request.user, request.password)
    }

    companion object {
        const val URL_GOOGLE = "/auth/google"
        const val URL_BASIC = "/auth/basic"
    }

}
