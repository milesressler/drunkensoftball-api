package com.drunkensoftball.api.bat.service;

import com.drunkensoftball.api.bat.domain.AtBat;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface BatService {

    AtBat addPlays (@NotBlank(message = "Authentication token required") String token,
                    @NotBlank(message = "gameUuid required") String gameUuid,
                    @NotBlank(message = "rosterUuid required") String rosterUuid,
                    @NotBlank(message = "atBatResult required") String atBatResultString,
                    @NotBlank(message = "uniqueId required") String uniqueId);

}
