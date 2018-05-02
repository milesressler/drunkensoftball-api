package com.drunkensoftball.api.team.service

import com.drunkensoftball.api.team.domain.Team
import com.drunkensoftball.api.user.domain.User
import org.springframework.data.domain.Pageable

interface TeamService {

    fun createTeam(user: User,
                   name: String): Team

    fun getTeams(user: User,
                 pageable: Pageable): List<Team>

    fun getTeam(user: User,
                teamUuid: String): Team

    fun updateTeam(user: User,
                   teamUuid: String,
                   name: String?): Team

    fun deleteTeam(user: User,
                   teamUuid: String)
}
