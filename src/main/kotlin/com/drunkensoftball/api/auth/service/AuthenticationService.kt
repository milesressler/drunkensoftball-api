package com.drunkensoftball.api.auth.service

import com.drunkensoftball.api.auth.domain.Authentication
import com.drunkensoftball.api.user.domain.User

interface AuthenticationService {

    fun getUserFromToken(token: String): User

    fun googleAuthentication(googleToken: String): Authentication

    fun loginWithBasicAuthentication(user: String,
                                     password: String): Authentication

    fun createNewUserBasicAuthentication(username: String,
                                         password: String,
                                         email: String,
                                         firstName: String?,
                                         lastName: String?): Authentication


}
