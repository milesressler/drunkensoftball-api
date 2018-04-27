package com.drunkensoftball.api.user.repo


import com.drunkensoftball.api.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findFirstByUsernameOrEmail(username: String, email: String): Optional<User>
}
