package com.drunkensoftball.api.roster.service


import com.drunkensoftball.api.roster.domain.FieldPosition
import com.drunkensoftball.api.roster.domain.RosterEntry
import com.drunkensoftball.api.roster.repo.RosterRepository
import com.drunkensoftball.api.service.AbstractService
import com.drunkensoftball.api.team.repo.TeamRepository
import com.drunkensoftball.api.user.domain.User
import com.drunkensoftball.api.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class DefaultRosterService : AbstractService(), RosterService {

    @Autowired
    lateinit var rosterRepository: RosterRepository

    @Autowired
    lateinit var teamRepository: TeamRepository

    @Autowired
    lateinit var userService: UserService

    override fun addPlayerByUuid(token: String,
                                 teamUuid: String,
                                 playerUuid: String,
                                 battingPosition: Int?,
                                 fieldPosition: String?): RosterEntry {
        return RosterEntry()
    }

    override fun addGuestPlayer(token: String,
                       teamUuid: String,
                       firstName: String,
                       lastName: String?,
                       battingPosition: Int?,
                       fieldPositionString: String?): RosterEntry {

        val user = getUserFromToken(token)
        val team = teamRepository.findByUuidAndManagerId(teamUuid, user.id)
        //        final User guestPlayer = userService.createUser(StringUtils.delete(UUID.randomUUID().toString(), "-" ), displayName, null);
        val guestPlayer: User? = null

        val rosterEntry = RosterEntry()
        rosterEntry.team = team
        rosterEntry.user = guestPlayer
        rosterEntry.battingPosition = battingPosition
        rosterEntry.fieldPosition = FieldPosition.getByString(fieldPositionString)

        return rosterRepository.save(rosterEntry)
    }
}
