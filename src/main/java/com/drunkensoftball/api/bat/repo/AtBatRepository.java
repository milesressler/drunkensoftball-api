package com.drunkensoftball.api.bat.repo;

import com.drunkensoftball.api.bat.domain.AtBat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtBatRepository extends JpaRepository<AtBat, Long> {

    AtBat findByUuid(String uuid);
    AtBat findByUniqueIdAndGameIdAndRosterEntryId(String uniqueId, Long gameId, Long rosterId);

    List<AtBat> findByRosterEntryId(Long rosterId, Pageable pageable);
    List<AtBat> findByGameId(Long gameId, Pageable pageable);

}
