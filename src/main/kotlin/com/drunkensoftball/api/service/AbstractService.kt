package com.drunkensoftball.api.service

import com.drunkensoftball.api.auth.repo.AuthenticationRepository
import com.drunkensoftball.api.exception.NotFoundException
import com.drunkensoftball.api.user.domain.User
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractService {

    @Autowired
    lateinit var authenticationRepository: AuthenticationRepository

    fun <T> mustExist(o: T?): T {
        return o ?: throw NotFoundException("Can't find entity")
    }

    fun getUserFromToken(token: String): User {
        return mustExist(authenticationRepository.findByToken(token).user)
    }

}
