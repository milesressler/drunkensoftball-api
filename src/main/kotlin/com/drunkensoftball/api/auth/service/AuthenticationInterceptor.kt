package com.drunkensoftball.api.auth.service

import com.drunkensoftball.api.auth.domain.DSAuthentication
import com.drunkensoftball.api.auth.repo.AuthenticationRepository
import com.drunkensoftball.api.exception.UnauthorizedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order
class AuthenticationInterceptor : HandlerInterceptor {

    @Autowired
    lateinit var authenticationRepository: AuthenticationRepository

    override fun preHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?): Boolean {
        val token: String? = request?.getHeader("Authorization")
        val dsAuthentication = DSAuthentication()
        if (!token.isNullOrEmpty()) {
            val authenticationEntity = authenticationRepository.findByToken(token!!)
            if (authenticationEntity == null) {
                throw UnauthorizedException("Access token invalid")
            } else {
                dsAuthentication.authenticationEntity = authenticationEntity
            }
        }
        SecurityContextHolder.getContext().authentication = dsAuthentication
        return true
    }

    override fun postHandle(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, modelAndView: ModelAndView?) {
    }

    override fun afterCompletion(request: HttpServletRequest?, response: HttpServletResponse?, handler: Any?, ex: Exception?) {
    }

}