package com.drunkensoftball.api.auth.domain

import com.drunkensoftball.api.domain.BaseEntity
import com.drunkensoftball.api.user.domain.User
import javax.persistence.*

@Entity
@Table(name = "google_authentication")
class GoogleAuthentication : BaseEntity() {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", nullable = false)
    var user: User? = null

    @Column(name = "google_id", nullable = false)
    var googleId: String? = null
}
