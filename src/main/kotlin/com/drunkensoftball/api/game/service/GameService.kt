package com.drunkensoftball.api.game.service

import com.drunkensoftball.api.auth.domain.DSAuthentication
import com.drunkensoftball.api.game.domain.Game
import com.drunkensoftball.api.user.domain.User

interface GameService {

    fun createGame(user: User,
                   teamUuid: String,
                   opponentName: String?): Game
}
