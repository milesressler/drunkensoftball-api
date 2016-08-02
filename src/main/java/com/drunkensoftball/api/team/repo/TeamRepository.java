package com.drunkensoftball.api.team.repo;

import com.drunkensoftball.api.team.domain.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByUuid(String uuid);

    Team findByUuidAndManagerId(String uuid, Long managerUserId);

    List<Team> findByManagerId(Long managerUserId, Pageable pageable);

}
