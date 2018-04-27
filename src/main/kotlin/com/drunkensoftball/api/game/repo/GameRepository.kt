package com.drunkensoftball.api.game.repo

import com.drunkensoftball.api.game.domain.Game
import com.drunkensoftball.api.team.domain.Team
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface GameRepository : JpaRepository<Game, Long> {

    fun findByTeamId(id: Long?): Game
    fun findByUuid(uuid: String): Game

}
