package com.drunkensoftball.api.team.repo

import com.drunkensoftball.api.team.domain.Team
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long> {

    fun findByUuid(uuid: String): Team

    fun findByUuidAndManagerId(uuid: String, managerUserId: Long?): Team

    fun findByManagerId(managerUserId: Long?, pageable: Pageable): List<Team>

}
