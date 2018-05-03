package com.drunkensoftball.api.auth.service

import org.springframework.stereotype.Component

@Component
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthenticationRequired