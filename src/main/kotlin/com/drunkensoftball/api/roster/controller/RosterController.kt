package com.drunkensoftball.api.roster.controller

import com.drunkensoftball.api.roster.domain.RosterEntry
import com.drunkensoftball.api.roster.domain.RosterRequest
import com.drunkensoftball.api.roster.service.RosterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.POST

@RestController
class RosterController {

    @Autowired
    lateinit var rosterService: RosterService

    @RequestMapping(value = [URL_ROSTER], method = [POST])
    fun addPlayer(@RequestHeader(name = "Authorization") token: String, @RequestBody rosterRequest: RosterRequest): RosterEntry {

        return rosterService.addGuestPlayer(token,
                rosterRequest.teamUuid,
                rosterRequest.firstName,
                rosterRequest.lastName,
                rosterRequest.battingPosition,
                rosterRequest.fieldPosition)
    }

    companion object {
        private const val URL_ROSTER = "/roster"
    }


}
