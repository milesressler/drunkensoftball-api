package com.drunkensoftball.api.bat.service

import com.drunkensoftball.api.bat.domain.AtBat

interface BatService {

    fun addPlays(token: String,
                 gameUuid: String,
                 rosterUuid: String,
                 atBatResultString: String,
                 uniqueId: String): AtBat

}
