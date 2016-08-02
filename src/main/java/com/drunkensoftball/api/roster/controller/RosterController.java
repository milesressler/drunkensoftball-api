package com.drunkensoftball.api.roster.controller;

import com.drunkensoftball.api.roster.domain.RosterRequest;
import com.drunkensoftball.api.roster.service.RosterService;
import com.drunkensoftball.api.team.domain.Team;
import com.drunkensoftball.api.team.domain.TeamRequest;
import com.drunkensoftball.api.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RosterController {

    @Autowired
    RosterService rosterService;

    private static final String URL_ROSTER = "/roster";

    @RequestMapping(value = URL_ROSTER, method = RequestMethod.POST)
    public String addPlayer(@RequestBody final RosterRequest rosterRequest, @RequestHeader(name = "Authorization") final String token){

        return rosterService.addGuestPlayer(token,
                rosterRequest.getTeamUuid(),
                rosterRequest.getDisplayName(),
                rosterRequest.getBattingPosition(),
                rosterRequest.getFieldPosition() ).getUuid();
    }


}
