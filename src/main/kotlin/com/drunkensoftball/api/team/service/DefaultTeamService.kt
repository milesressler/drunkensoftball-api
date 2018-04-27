package com.drunkensoftball.api.team.service


import com.drunkensoftball.api.roster.domain.RosterEntry
import com.drunkensoftball.api.roster.repo.RosterRepository
import com.drunkensoftball.api.service.AbstractService
import com.drunkensoftball.api.team.domain.Team
import com.drunkensoftball.api.team.repo.TeamRepository
import com.drunkensoftball.api.user.domain.User
import com.drunkensoftball.api.user.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.LinkedList

@Service
open class DefaultTeamService : AbstractService(), TeamService {

    @Autowired
    lateinit var teamRepository: TeamRepository

    @Autowired
    lateinit var rosterRepository: RosterRepository

    @Autowired
    lateinit var userRepository: UserRepository

    override fun createTeam(token: String,
                            name: String): Team {

        val user = mustExist(authenticationRepository.findByToken(token)).user
        val team = Team()
        team.name = name
        team.manager = user

        val rosterEntry = RosterEntry()
        rosterEntry.team = team
        rosterEntry.user = user

        val playerList = LinkedList<RosterEntry>()
        playerList.add(rosterEntry)
        team.players = playerList

        return teamRepository.save(team)
    }

    override fun getTeams(token: String,
                          pageable: Pageable): List<Team> {

        val manager = mustExist(authenticationRepository.findByToken(token)).user
        return teamRepository.findByManagerId(manager!!.id, pageable)
    }

    override fun getTeam(token: String,
                         teamUuid: String): Team {

        val manager = mustExist(authenticationRepository.findByToken(token)).user
        return mustExist(teamRepository.findByUuidAndManagerId(teamUuid, manager!!.id))
    }

    @Transactional(rollbackFor = [Throwable::class])
    override fun deleteTeam(token: String,
                            teamUuid: String) {

        val manager = mustExist(authenticationRepository.findByToken(token)).user
        val team = mustExist(teamRepository.findByUuidAndManagerId(teamUuid, manager!!.id))

        for (rosterEntry in rosterRepository.findByTeamId(team.id)) {

            val numberOfTeams = rosterRepository.findByUserId(rosterEntry.user!!.id).size

            rosterEntry.removed = true
            rosterRepository.save(rosterEntry)

            if (numberOfTeams <= 1) {
                rosterEntry.user!!.removed = true
                userRepository.save<User>(rosterEntry.user)
                // TODO use user service isntead
            }

        }
        team.removed = true
        teamRepository.save(team)


    }
}
