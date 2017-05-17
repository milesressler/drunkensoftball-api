package com.drunkensoftball.api.roster.service;

import com.drunkensoftball.api.roster.domain.RosterEntry;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface RosterService {

    RosterEntry addPlayerByUuid(@NotBlank(message = "Authentication token required") String token,
                                @NotBlank(message = "Team uuid required") String teamUuid,
                                @NotBlank(message = "Player UUID required") String playerUuid,
                                Integer battingPosition,
                                String fieldPosition);

    RosterEntry addGuestPlayer(@NotBlank(message = "Authentication token required") String token,
                               @NotBlank(message = "Team uuid required") String teamUuid,
                               @NotBlank(message = "Display name required.") String displayName,
                               Integer battingPosition,
                               String fieldPosition);

}
