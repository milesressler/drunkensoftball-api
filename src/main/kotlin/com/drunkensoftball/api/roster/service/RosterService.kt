package com.drunkensoftball.api.roster.service


import com.drunkensoftball.api.auth.domain.DSAuthentication
import com.drunkensoftball.api.roster.domain.RosterEntry

interface RosterService {

    fun addPlayerByUuid(authentication: DSAuthentication,
                        teamUuid: String,
                        playerUuid: String,
                        battingPosition: Int?,
                        fieldPosition: String?): RosterEntry

    fun addGuestPlayer(authentication: DSAuthentication,
                       teamUuid: String,
                       firstName: String,
                       lastName: String?,
                       battingPosition: Int?,
                       fieldPositionString: String?): RosterEntry

}
