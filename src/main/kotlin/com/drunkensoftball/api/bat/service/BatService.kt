package com.drunkensoftball.api.bat.service

import com.drunkensoftball.api.auth.domain.DSAuthentication
import com.drunkensoftball.api.bat.domain.AtBat

interface BatService {

    fun addPlays(authentication: DSAuthentication,
                 gameUuid: String,
                 rosterUuid: String,
                 atBatResultString: String,
                 uniqueId: String): AtBat

}
