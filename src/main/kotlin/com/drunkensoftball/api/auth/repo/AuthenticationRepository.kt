package com.drunkensoftball.api.auth.repo

import com.drunkensoftball.api.auth.domain.AuthenticationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface AuthenticationRepository : JpaRepository<AuthenticationEntity, Long> {
    fun findByToken(token: String): AuthenticationEntity?
}
