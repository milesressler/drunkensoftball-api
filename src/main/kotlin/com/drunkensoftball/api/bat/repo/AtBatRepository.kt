package com.drunkensoftball.api.bat.repo


import com.drunkensoftball.api.bat.domain.AtBat
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface AtBatRepository : JpaRepository<AtBat, Long> {

    fun findByUuid(uuid: String): AtBat?
    fun findByUniqueIdAndGameIdAndRosterEntryId(uniqueId: String, gameId: Long?, rosterId: Long?): AtBat?

    fun findByRosterEntryId(rosterId: Long?, pageable: Pageable): List<AtBat>
    fun findByGameId(gameId: Long?, pageable: Pageable): List<AtBat>

}
