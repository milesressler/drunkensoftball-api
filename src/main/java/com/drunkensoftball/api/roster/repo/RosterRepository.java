package com.drunkensoftball.api.roster.repo;

import com.drunkensoftball.api.roster.domain.Roster;
import com.drunkensoftball.api.team.domain.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RosterRepository extends JpaRepository<Roster, Long> {

    Roster findByUuid(String uuid);

    List<Roster> findByTeamId(Long teamId, Pageable pageable);
    List<Roster> findByUserId(Long userId, Pageable pageable);

}
