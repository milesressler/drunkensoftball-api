package com.drunkensoftball.api.bat.service

import com.drunkensoftball.api.bat.domain.AtBat
import com.drunkensoftball.api.user.domain.User

interface BatService {

    fun addPlays(user: User,
                 gameUuid: String,
                 rosterUuid: String,
                 atBatResultString: String,
                 uniqueId: String,
                 rbis: Int): Int

}
