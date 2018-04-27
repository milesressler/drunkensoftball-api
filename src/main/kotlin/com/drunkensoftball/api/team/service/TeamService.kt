package com.drunkensoftball.api.team.service

import com.drunkensoftball.api.team.domain.Team
import org.springframework.data.domain.Pageable
import org.springframework.validation.annotation.Validated

@Validated
interface TeamService {

    fun createTeam(token: String,
                   name: String): Team

    fun getTeams(token: String,
                 pageable: Pageable): List<Team>

    fun getTeam(token: String,
                teamUuid: String): Team

    fun deleteTeam(token: String,
                   teamUuid: String)
}
