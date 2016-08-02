package com.drunkensoftball.api.game.service;

import com.drunkensoftball.api.game.domain.Game;
import com.drunkensoftball.api.game.repo.GameRepository;
import com.drunkensoftball.api.service.AbstractService;
import com.drunkensoftball.api.team.domain.Team;
import com.drunkensoftball.api.team.repo.TeamRepository;
import com.drunkensoftball.api.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultGameService extends AbstractService implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Game createGame(final String token,
                           final String teamUuid) {

        final User user = getUserFromToken(token);
        final Team team = mustExist(teamRepository.findByUuidAndManagerId(teamUuid, user.getId()));
        final Game game = new Game();
        game.setTeam(team);

        return gameRepository.save(game);
    }
}
