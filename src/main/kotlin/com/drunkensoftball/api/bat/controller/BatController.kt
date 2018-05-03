package com.drunkensoftball.api.bat.controller


import com.drunkensoftball.api.auth.service.AuthenticationRequired
import com.drunkensoftball.api.bat.domain.BatRequest
import com.drunkensoftball.api.bat.service.BatService
import com.drunkensoftball.api.user.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RestController

@RestController
class BatController {

    @Autowired
    lateinit var batService: BatService

    @AuthenticationRequired
    @RequestMapping(value = [URL_BAT], method = [POST])
    fun batBulkUpload(@AuthenticationPrincipal user: User, @RequestBody(required = true) batRequestList: List<BatRequest>): Any? {
        var resultsAdded = 0
        var resultsProcessed = 0

        for (batRequest in batRequestList) {
            resultsAdded += batService.addPlays(user, batRequest.gameUuid, batRequest.rosterUuid, batRequest.atBatResult, batRequest.uniqueId, batRequest.rbis)
            resultsProcessed += 1
        }
        return mapOf("newCount" to resultsAdded, "processedCount" to resultsProcessed)
    }

    companion object {

        private const val URL_BAT = "/bat"
    }

}
