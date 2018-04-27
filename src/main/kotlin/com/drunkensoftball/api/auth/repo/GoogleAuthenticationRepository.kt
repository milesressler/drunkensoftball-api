package com.drunkensoftball.api.auth.repo

import com.drunkensoftball.api.auth.domain.GoogleAuthentication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GoogleAuthenticationRepository : JpaRepository<GoogleAuthentication, Long> {

    fun findByGoogleId(googleId: String): Optional<GoogleAuthentication>
}
