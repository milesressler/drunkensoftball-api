package com.drunkensoftball.api.roster.repo


import com.drunkensoftball.api.roster.domain.RosterEntry
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface RosterRepository : JpaRepository<RosterEntry, Long> {

    fun findByUuid(uuid: String): RosterEntry

    fun findByTeamId(teamId: Long?): List<RosterEntry>
    fun findByTeamId(teamId: Long?, pageable: Pageable): List<RosterEntry>
    fun findByUserId(userId: Long?): List<RosterEntry>
    fun findByUserId(userId: Long?, pageable: Pageable): List<RosterEntry>


}
