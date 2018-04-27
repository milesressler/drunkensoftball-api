package com.drunkensoftball.api.roster.domain


import com.drunkensoftball.api.domain.BaseEntity
import com.drunkensoftball.api.team.domain.Team
import com.drunkensoftball.api.user.domain.User
import com.fasterxml.jackson.databind.annotation.JsonSerialize

import javax.persistence.*

@Entity
@Table(name = "roster")
@JsonSerialize(using = RosterSerializer::class)
class RosterEntry : BaseEntity() {

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    var user: User? = null

    @ManyToOne
    @JoinColumn(name = "team", nullable = false)
    var team: Team? = null

    @Column(name = "batting_position")
    var battingPosition: Int? = null

    @Column(name = "field_position")
    var fieldPosition: FieldPosition? = null
}
