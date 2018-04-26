package com.drunkensoftball.api.user.domain


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

    @Column(name = "display_name")
    var displayName: String? = null

    @Column(name = "verified", nullable = false)
    var verified: Boolean? = null

    @Column(name = "invite_sent", nullable = false)
    var inviteSent: Boolean? = null

    @OneToMany(mappedBy = "user")
    var teams: MutableList<RosterEntry>? = null

    @OneToMany(mappedBy = "manager")
    var managedTeams: MutableList<Team>? = null
}
