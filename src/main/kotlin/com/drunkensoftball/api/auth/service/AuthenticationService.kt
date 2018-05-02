package com.drunkensoftball.api.auth.service

import com.drunkensoftball.api.auth.domain.AuthenticationEntity
import com.drunkensoftball.api.user.domain.User

interface AuthenticationService {

    fun getUserFromToken(token: String): User

    fun googleAuthentication(googleToken: String): AuthenticationEntity

    fun loginWithBasicAuthentication(user: String,
                                     password: String): AuthenticationEntity

    fun createNewUserBasicAuthentication(username: String,
                                         password: String,
                                         email: String,
                                         firstName: String?,
                                         lastName: String?): AuthenticationEntity


}
