package com.drunkensoftball.api.auth.service

import com.drunkensoftball.api.exception.UnauthorizedException
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Aspect
@Component
class AuthenticationRequiredAspect {

    @Before("@annotation(com.drunkensoftball.api.auth.service.AuthenticationRequired)")
    fun before(joinPoint: JoinPoint) {
        if (SecurityContextHolder.getContext().authentication.principal == null) {
            throw UnauthorizedException("Invalid authorization.")
        }
    }
}