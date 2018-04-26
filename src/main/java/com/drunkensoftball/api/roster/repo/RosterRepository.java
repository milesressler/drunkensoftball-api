package com.drunkensoftball.api.roster.repo;

import com.drunkensoftball.api.roster.domain.RosterEntry;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RosterRepository extends JpaRepository<RosterEntry, Long> {

    RosterEntry findByUuid(String uuid);

    List<RosterEntry> findByTeamId(Long teamId);
    List<RosterEntry> findByTeamId(Long teamId, Pageable pageable);
    List<RosterEntry> findByUserId(Long userId);
    List<RosterEntry> findByUserId(Long userId, Pageable pageable);


}
