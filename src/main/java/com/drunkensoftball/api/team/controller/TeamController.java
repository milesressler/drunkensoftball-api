package com.drunkensoftball.api.team.controller;

import com.drunkensoftball.api.roster.domain.RosterRequest;
import com.drunkensoftball.api.team.domain.Team;
import com.drunkensoftball.api.team.domain.TeamRequest;
import com.drunkensoftball.api.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    private static final String URL_TEAM = "/team";

    @RequestMapping(value = URL_TEAM, method = RequestMethod.POST)
    public String createTeam(@RequestBody final TeamRequest teamRequest, @RequestHeader(name = "Authorization") final String token){

        return teamService.createTeam(token, teamRequest.getName()).getUuid();

    }
    @RequestMapping(value = URL_TEAM, method = RequestMethod.GET)
    public List<Team> getTeams(@RequestHeader(name = "Authorization") final String token, final Pageable pageable){

        return teamService.getTeams(token, pageable);
    }

    @RequestMapping(value = URL_TEAM + "/{uuid}", method = RequestMethod.GET)
    public Team getTeam(@RequestHeader(name = "Authorization") final String token, @PathVariable(value = "uuid") final String teamUuid){

        return teamService.getTeam(token, teamUuid);
    }


}
