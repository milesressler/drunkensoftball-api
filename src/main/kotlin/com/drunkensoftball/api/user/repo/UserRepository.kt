package com.drunkensoftball.api.user.repo


import com.drunkensoftball.api.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findFirstByUsernameOrEmail(username: String, email: String): Optional<User>
}
