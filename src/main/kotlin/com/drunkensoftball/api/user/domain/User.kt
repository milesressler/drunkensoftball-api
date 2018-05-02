package com.drunkensoftball.api.user.domain


import com.drunkensoftball.api.auth.domain.BasicAuthentication
import com.drunkensoftball.api.domain.BaseEntity
import com.drunkensoftball.api.roster.domain.RosterEntry
import com.drunkensoftball.api.team.domain.Team
import com.fasterxml.jackson.databind.annotation.JsonSerialize

import javax.persistence.*


@Entity
@Table(name = "user")
@JsonSerialize(using = UserSerializer::class)
class User : BaseEntity() {

    @Column(name = "email")
    var email: String? = null

    @Column(name = "username", unique = true, nullable = false)
    var username: String? = null

    @Column(name = "first_name")
    var firstName: String? = null

    @Column(name = "last_name")
    var lastName: String? = null

    @Column(name = "verified", nullable = false)
    var verified: Boolean? = null

    @OneToMany(mappedBy = "user")
    var teams: MutableList<RosterEntry>? = null

    @OneToMany(mappedBy = "manager")
    var managedTeams: MutableList<Team>? = null

    @OneToOne(mappedBy = "user")
    var basicAuthentication: BasicAuthentication? = null

    fun displayName(): String? {
        val fullName = "${firstName ?: ""} ${lastName ?: ""}".trim()
        return if(fullName.isNotEmpty()) fullName else username
    }
}
