package com.drunkensoftball.api.auth.repo

import com.drunkensoftball.api.auth.domain.BasicAuthentication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BasicAuthenticationRepository : JpaRepository<BasicAuthentication, Long> {

    fun findByUserUsername(username: String): BasicAuthentication
}
