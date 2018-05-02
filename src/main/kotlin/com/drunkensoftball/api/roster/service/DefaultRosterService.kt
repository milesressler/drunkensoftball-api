package com.drunkensoftball.api.roster.service


import com.drunkensoftball.api.roster.domain.FieldPosition
import com.drunkensoftball.api.roster.domain.RosterEntry
import com.drunkensoftball.api.roster.repo.RosterRepository
import com.drunkensoftball.api.service.AbstractService
import com.drunkensoftball.api.team.repo.TeamRepository
import com.drunkensoftball.api.user.domain.User
import com.drunkensoftball.api.user.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class DefaultRosterService : AbstractService(), RosterService {

    @Autowired
    lateinit var rosterRepository: RosterRepository

    @Autowired
    lateinit var teamRepository: TeamRepository

    @Autowired
    lateinit var userRepository: UserRepository

    override fun addPlayerByUuid(user: User,
                                 teamUuid: String,
                                 playerUuid: String,
                                 battingPosition: Int?,
                                 fieldPosition: String?): RosterEntry {
        return RosterEntry()
    }

    override fun addGuestPlayer(user: User,
                       teamUuid: String,
                       firstName: String,
                       lastName: String?,
                       battingPosition: Int?,
                       fieldPositionString: String?): RosterEntry {

        val team = mustExist(teamRepository.findByUuidAndManagerId(teamUuid, user.id))

        var guestPlayer = User()
        guestPlayer.firstName = firstName.trim()
        guestPlayer.lastName = lastName?.trim()
        guestPlayer.username = "${firstName.trim().toLowerCase()}.${lastName?.trim()?.toLowerCase() ?: ""}${System.currentTimeMillis() / 1000}"
        guestPlayer.verified = false
        guestPlayer = userRepository.save(guestPlayer)

        val rosterEntry = RosterEntry()
        rosterEntry.team = team
        rosterEntry.user = guestPlayer
        rosterEntry.battingPosition = battingPosition
        rosterEntry.fieldPosition = FieldPosition.getByString(fieldPositionString)

        return rosterRepository.save(rosterEntry)
    }
}
