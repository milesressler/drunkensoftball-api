package com.drunkensoftball.api.team.controller


import com.drunkensoftball.api.team.domain.Team
import com.drunkensoftball.api.team.domain.CreateTeamRequest
import com.drunkensoftball.api.team.service.TeamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.*

@RestController
class TeamController {

    @Autowired
    lateinit var teamService: TeamService

    @RequestMapping(value = [URL_TEAM], method = [POST])
    fun createTeam(@RequestBody createTeamRequest: CreateTeamRequest, @RequestHeader(name = "Authorization") token: String): Team {
        return teamService.createTeam(token, createTeamRequest.name)
    }

    @RequestMapping(value = [URL_TEAM], method = [GET])
    fun getTeams(@RequestHeader(name = "Authorization") token: String, pageable: Pageable): List<Team> {

        return teamService.getTeams(token, pageable)
    }

    @RequestMapping(value = [URL_TEAM_UUID], method = [GET])
    fun getTeam(@RequestHeader(name = "Authorization") token: String, @PathVariable(value = UUID) teamUuid: String): Team {

        return teamService.getTeam(token, teamUuid)
    }

    @RequestMapping(value = [URL_TEAM_UUID], method = [DELETE])
    fun deleteTeam(@RequestHeader(name = "Authorization") token: String, @PathVariable(value = UUID) teamUuid: String) {
        teamService.deleteTeam(token, teamUuid)
    }

    companion object {
        private const val URL_TEAM = "/team"
        private const val URL_TEAM_UUID = "/team/{uuid}"
        private const val UUID = "uuid"
    }
}
