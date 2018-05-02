package com.drunkensoftball.api.game.domain


import com.drunkensoftball.api.domain.BaseEntity
import com.drunkensoftball.api.team.domain.Team
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import javax.persistence.*

@Entity
@Table(name = "game")
//@JsonSerialize(using = GameSerializer.class)
class Game : BaseEntity() {

    @ManyToOne(optional = false)
    @JoinColumn(name = "team")
    var team: Team? = null

    @Column(name = "opponent_name")
    var opponentName: String? = null
}
