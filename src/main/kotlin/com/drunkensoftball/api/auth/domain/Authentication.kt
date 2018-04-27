package com.drunkensoftball.api.auth.domain


import com.drunkensoftball.api.domain.BaseEntity
import com.drunkensoftball.api.user.domain.User
import com.fasterxml.jackson.databind.annotation.JsonSerialize

import javax.persistence.*

@Entity
@Table(name = "authentication")
@JsonSerialize(using = AuthenticationSerializer::class)
class Authentication : BaseEntity() {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", nullable = false)
    var user: User? = null

    @Column(name = "token", nullable = false)
    var token: String? = null
}
