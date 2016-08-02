package com.drunkensoftball.api.game.controller;

import com.drunkensoftball.api.auth.domain.AuthenticationType;
import com.drunkensoftball.api.auth.service.AuthenticationService;
import com.drunkensoftball.api.game.domain.Game;
import com.drunkensoftball.api.game.domain.GameRequest;
import com.drunkensoftball.api.game.service.GameService;
import com.drunkensoftball.api.user.domain.User;
import com.drunkensoftball.api.user.domain.UserRequest;
import com.drunkensoftball.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    GameService gameService;
    private static final String URL_GAME = "/game";

    @RequestMapping(value = URL_GAME, method = RequestMethod.POST)
    public String createGame(@RequestHeader(name = "Authorization") final String token, @RequestBody final GameRequest gameRequest) {

        final Game game = gameService.createGame(token, gameRequest.getTeamUuid());
        return game.getUuid();
    }


    @RequestMapping(value = URL_GAME + "/{uuid}", method = RequestMethod.PUT)
    public void updateGame(@PathVariable(value = "uuid") final String gameUuid) {


    }

}
