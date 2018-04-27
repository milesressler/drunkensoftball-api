package com.drunkensoftball.api.auth.repo

import com.drunkensoftball.api.auth.domain.Authentication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface AuthenticationRepository : JpaRepository<Authentication, Long> {
    fun findByToken(token: String): Authentication
}
