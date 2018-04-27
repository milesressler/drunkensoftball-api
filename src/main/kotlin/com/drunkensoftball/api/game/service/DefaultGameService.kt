package com.drunkensoftball.api.game.service


import com.drunkensoftball.api.game.domain.Game
import com.drunkensoftball.api.game.repo.GameRepository
import com.drunkensoftball.api.service.AbstractService
import com.drunkensoftball.api.team.repo.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DefaultGameService : AbstractService(), GameService {

    @Autowired
    lateinit var gameRepository: GameRepository

    @Autowired
    lateinit var teamRepository: TeamRepository

    override fun createGame(token: String,
                            teamUuid: String): Game {

        val user = getUserFromToken(token)
        val team = mustExist(teamRepository.findByUuidAndManagerId(teamUuid, user.id))

        val game = Game()
        game.team = team

        return gameRepository.save(game)
    }
}
