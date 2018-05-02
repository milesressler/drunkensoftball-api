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

    override fun createTeam(user: User,
                            name: String): Team {

        val team = Team()
        team.name = name
        team.manager = user

        val rosterEntry = RosterEntry()
        rosterEntry.team = team
        rosterEntry.user = user

        val playerList = LinkedList<RosterEntry>()
        playerList.add(rosterEntry)
        team.roster = playerList

        return teamRepository.save(team)
    }

    override fun getTeams(user: User,
                          pageable: Pageable): List<Team> {

        return teamRepository.findByManagerId(user.id, pageable)
    }

    override fun getTeam(user: User,
                         teamUuid: String): Team {

        return mustExist(teamRepository.findByUuidAndManagerId(teamUuid, user.id))
    }

    @Transactional(rollbackFor = [Throwable::class])
    override fun deleteTeam(user: User,
                            teamUuid: String) {

        val team = mustExist(teamRepository.findByUuidAndManagerId(teamUuid, user.id))

        rosterRepository.findByTeamId(team.id).forEach({

            it.removed = true
            rosterRepository.save(it)

            // todo Remove unclaimed user accounts with no other teams
        })

        team.removed = true
        teamRepository.save(team)
    }

    override fun updateTeam(user: User, teamUuid: String, name: String?): Team {
        val team = mustExist(teamRepository.findByUuidAndManagerId(teamUuid, user.id))
        if (!name.isNullOrBlank()) {
            team.name = name
        }
        return teamRepository.save(team)
    }
}
