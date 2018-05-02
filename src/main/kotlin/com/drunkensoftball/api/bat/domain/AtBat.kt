package com.drunkensoftball.api.bat.domain


import com.drunkensoftball.api.domain.BaseEntity
import com.drunkensoftball.api.game.domain.Game
import com.drunkensoftball.api.roster.domain.RosterEntry

import javax.persistence.*

@Entity
@Table(name = "bat")
class AtBat : BaseEntity() {

    @ManyToOne
    @JoinColumn(name = "rosterEntry", nullable = false)
    var rosterEntry: RosterEntry? = null

    @ManyToOne
    @JoinColumn(name = "game", nullable = false)
    var game: Game? = null

    @Column(name = "unique_id")
    var uniqueId: String? = null

    @Column(name = "result")
    var atBatResult: AtBatResult? = null

    @Column(name = "rbis")
    var rbis: Int = 0
}
