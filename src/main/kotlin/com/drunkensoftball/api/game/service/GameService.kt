package com.drunkensoftball.api.game.service

import com.drunkensoftball.api.auth.domain.DSAuthentication
import com.drunkensoftball.api.game.domain.Game

interface GameService {

    fun createGame(authentication: DSAuthentication,
                   teamUuid: String): Game
}
