package com.drunkensoftball.api.game.controller

import com.drunkensoftball.api.game.domain.Game
import com.drunkensoftball.api.game.domain.GameRequest
import com.drunkensoftball.api.game.service.GameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestMethod.PUT

@RestController
class GameController {

    @Autowired
    lateinit var gameService: GameService

    @RequestMapping(value = [URL_GAME], method = [POST])
    fun createGame(@RequestHeader(name = "Authorization") token: String, @RequestBody gameRequest: GameRequest): Game {

        return gameService.createGame(token, gameRequest.teamUuid)
    }


    @RequestMapping(value = [URL_GAME_UUID], method = [PUT])
    fun updateGame(@PathVariable(value = UUID) gameUuid: String) {


    }

    companion object {
        private const val URL_GAME = "/game"
        private const val URL_GAME_UUID = "/game/{uuid}"
        private const val UUID = "uuid"
    }

}
