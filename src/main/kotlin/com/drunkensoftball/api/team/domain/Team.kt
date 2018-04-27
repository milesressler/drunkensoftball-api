package com.drunkensoftball.api.team.domain


import com.drunkensoftball.api.domain.BaseEntity
import com.drunkensoftball.api.roster.domain.RosterEntry
import com.drunkensoftball.api.user.domain.User
import com.fasterxml.jackson.databind.annotation.JsonSerialize

import javax.persistence.*


@Entity
@Table(name = "team")
@JsonSerialize(using = TeamSerializer::class)
class Team : BaseEntity() {

    @Column(name = "name")
    var name: String? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "manager")
    var manager: User? = null

    @OneToMany(mappedBy = "team", cascade = arrayOf(CascadeType.ALL))
    var players: MutableList<RosterEntry>? = null
}
