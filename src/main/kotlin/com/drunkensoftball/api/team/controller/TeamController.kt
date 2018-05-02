package com.drunkensoftball.api.team.controller


import com.drunkensoftball.api.auth.domain.DSAuthentication
import com.drunkensoftball.api.team.domain.Team
import com.drunkensoftball.api.team.domain.CreateTeamRequest
import com.drunkensoftball.api.team.service.TeamService
import com.drunkensoftball.api.user.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.*

@RestController
class TeamController {

    @Autowired
    lateinit var teamService: TeamService

    @RequestMapping(value = [URL_TEAM], method = [POST])
    fun createTeam(@AuthenticationPrincipal user: User,
                   @RequestBody createTeamRequest: CreateTeamRequest): Team {
        return teamService.createTeam(user, createTeamRequest.name)
    }

    @RequestMapping(value = [URL_TEAM], method = [PUT])
    fun updateTeam(@AuthenticationPrincipal user: User,
                   @RequestBody request: CreateTeamRequest,
                   @PathVariable(value = UUID) teamUuid: String): Team {
        return teamService.updateTeam(user, teamUuid, request.name)
    }

    @RequestMapping(value = [URL_TEAM], method = [GET])
    fun getTeams(@AuthenticationPrincipal user: User,
                 pageable: Pageable): List<Team> {

        return teamService.getTeams(user, pageable)
    }

    @RequestMapping(value = [URL_TEAM_UUID], method = [GET])
    fun getTeam(@AuthenticationPrincipal user: User,
                @PathVariable(value = UUID) teamUuid: String): Team {

        return teamService.getTeam(user, teamUuid)
    }

    @RequestMapping(value = [URL_TEAM_UUID], method = [DELETE])
    fun deleteTeam(@AuthenticationPrincipal user: User,
                   @PathVariable(value = UUID) teamUuid: String) {
        teamService.deleteTeam(user, teamUuid)
    }

    companion object {
        private const val URL_TEAM = "/team"
        private const val URL_TEAM_UUID = "/team/{uuid}"
        private const val UUID = "uuid"
    }
}
