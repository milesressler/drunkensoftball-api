package com.drunkensoftball.api.game.repo;

import com.drunkensoftball.api.game.domain.Game;
import com.drunkensoftball.api.team.domain.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByTeamId(Long id);

}
