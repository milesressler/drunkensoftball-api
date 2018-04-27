package com.drunkensoftball.api.auth.domain

import com.drunkensoftball.api.domain.BaseEntity
import com.drunkensoftball.api.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "basic_authentication")
class BasicAuthentication : BaseEntity() {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", nullable = false)
    var user: User? = null

    @Column(name = "password_hash")
    var passwordHash: String? = null
}