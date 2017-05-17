package com.drunkensoftball.api.bat.controller;

import com.drunkensoftball.api.bat.domain.AtBat;
import com.drunkensoftball.api.bat.domain.BatRequest;
import com.drunkensoftball.api.bat.service.BatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BatController {

    @Autowired
    BatService batService;

    private static final String URL_BAT = "/bat";

//    @RequestMapping(value = URL_BAT, method = RequestMethod.POST)
//    public String batUpload(@RequestHeader(name = "Authorization") final String token, @RequestBody final BatRequest batRequest) {
//        return batService.addPlays(token, batRequest.getGameUuid(), batRequest.getRosterUuid(), batRequest.getAtBatResult(), batRequest.getUniqueId()).getUuid();
//    }

    @RequestMapping(value = URL_BAT, method = RequestMethod.POST)
    public Integer batBulkUpload(@RequestHeader(name = "Authorization") final String token, @RequestBody(required = true) final List<BatRequest> batRequestList) {
        int resultsAdded = 0;
        for(final BatRequest batRequest : batRequestList) {
            batService.addPlays(token, batRequest.getGameUuid(), batRequest.getRosterUuid(), batRequest.getAtBatResult(), batRequest.getUniqueId());
            resultsAdded +=1;
        }
        return resultsAdded;
    }

}
