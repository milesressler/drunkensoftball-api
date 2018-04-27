package com.drunkensoftball.api.game.service

import com.drunkensoftball.api.game.domain.Game

interface GameService {

    fun createGame(token: String,
                   teamUuid: String): Game
}
