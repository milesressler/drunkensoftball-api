package com.drunkensoftball.api.bat.controller


import com.drunkensoftball.api.auth.domain.DSAuthentication
import com.drunkensoftball.api.bat.domain.AtBat
import com.drunkensoftball.api.bat.domain.BatRequest
import com.drunkensoftball.api.bat.service.BatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.POST

@RestController
class BatController {

    @Autowired
    lateinit var batService: BatService

    //    @RequestMapping(value = URL_BAT, method = RequestMethod.POST)
    //    public String batUpload(@RequestHeader(name = "Authorization") final String token, @RequestBody final BatRequest batRequest) {
    //        return batService.addPlays(token, batRequest.getGameUuid(), batRequest.getRosterUuid(), batRequest.getAtBatResult(), batRequest.getUniqueId()).getUuid();
    //    }

    @RequestMapping(value = [URL_BAT], method = [POST])
    fun batBulkUpload(@AuthenticationPrincipal authentication: DSAuthentication, @RequestBody(required = true) batRequestList: List<BatRequest>): Int? {
        var resultsAdded = 0
        for (batRequest in batRequestList) {
            batService.addPlays(authentication, batRequest.gameUuid, batRequest.rosterUuid, batRequest.atBatResult, batRequest.uniqueId)
            resultsAdded += 1
        }
        return resultsAdded
    }

    companion object {

        private const val URL_BAT = "/bat"
    }

}
