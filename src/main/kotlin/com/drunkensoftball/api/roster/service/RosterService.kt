package com.drunkensoftball.api.roster.service


import com.drunkensoftball.api.auth.domain.DSAuthentication
import com.drunkensoftball.api.roster.domain.RosterEntry
import com.drunkensoftball.api.user.domain.User

interface RosterService {

    fun addPlayerByUuid(user: User,
                        teamUuid: String,
                        playerUuid: String,
                        battingPosition: Int?,
                        fieldPosition: String?): RosterEntry

    fun addGuestPlayer(user: User,
                       teamUuid: String,
                       firstName: String,
                       lastName: String?,
                       battingPosition: Int?,
                       fieldPositionString: String?): RosterEntry

}
