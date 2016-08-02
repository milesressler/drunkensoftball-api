package com.drunkensoftball.api.game.service;

import com.drunkensoftball.api.game.domain.Game;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface GameService {

    Game createGame(@NotBlank(message = "Authentication token required") String token,
                    @NotBlank(message = "Team UUID required") String teamUuid);
}
