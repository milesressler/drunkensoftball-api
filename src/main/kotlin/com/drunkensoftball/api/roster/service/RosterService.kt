package com.drunkensoftball.api.roster.service


import com.drunkensoftball.api.roster.domain.RosterEntry

interface RosterService {

    fun addPlayerByUuid(token: String,
                        teamUuid: String,
                        playerUuid: String,
                        battingPosition: Int?,
                        fieldPosition: String?): RosterEntry

    fun addGuestPlayer(token: String,
                       teamUuid: String,
                       firstName: String,
                       lastName: String?,
                       battingPosition: Int?,
                       fieldPositionString: String?): RosterEntry

}
