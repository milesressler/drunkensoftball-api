package com.drunkensoftball.api.bat.service


import com.drunkensoftball.api.auth.domain.DSAuthentication
import com.drunkensoftball.api.bat.domain.AtBat
import com.drunkensoftball.api.bat.domain.AtBatResult
import com.drunkensoftball.api.bat.repo.AtBatRepository
import com.drunkensoftball.api.game.domain.Game
import com.drunkensoftball.api.game.repo.GameRepository
import com.drunkensoftball.api.roster.domain.RosterEntry
import com.drunkensoftball.api.roster.repo.RosterRepository
import com.drunkensoftball.api.service.AbstractService
import com.drunkensoftball.api.user.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DefaultBatService : AbstractService(), BatService {

    @Autowired
    lateinit var rosterRepository: RosterRepository

    @Autowired
    lateinit var gameRepository: GameRepository

    @Autowired
    lateinit var atBatRepository: AtBatRepository

    override fun addPlays(authentication: DSAuthentication,
                          gameUuid: String,
                          rosterUuid: String,
                          atBatResultString: String,
                          uniqueId: String): AtBat {

        val user = authentication.authenticationEntity?.user

        // todo ownership validations

        val game = mustExist(gameRepository.findByUuid(gameUuid))
        val rosterEntry = mustExist(rosterRepository.findByUuid(rosterUuid))

        val existingAtBat = atBatRepository.findByUniqueIdAndGameIdAndRosterEntryId(uniqueId, game.id, rosterEntry.id)
        val atBatResult = AtBatResult.getByString(atBatResultString)

        if (existingAtBat == null) {

            val atBat = AtBat()
            atBat.game = game
            atBat.rosterEntry = rosterEntry
            atBat.atBatResult = atBatResult
            atBat.uniqueId = uniqueId

            return atBatRepository.save(atBat)

        } else {
            existingAtBat.atBatResult = atBatResult
            existingAtBat.uniqueId = uniqueId
            return atBatRepository.save(existingAtBat)
        }
    }
}