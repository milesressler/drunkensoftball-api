package com.drunkensoftball.api.bat.service;


import com.drunkensoftball.api.bat.domain.AtBat;
import com.drunkensoftball.api.bat.domain.AtBatResult;
import com.drunkensoftball.api.bat.repo.AtBatRepository;
import com.drunkensoftball.api.game.domain.Game;
import com.drunkensoftball.api.game.repo.GameRepository;
import com.drunkensoftball.api.roster.domain.RosterEntry;
import com.drunkensoftball.api.roster.repo.RosterRepository;
import com.drunkensoftball.api.service.AbstractService;
import com.drunkensoftball.api.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultBatService extends AbstractService implements BatService {

    @Autowired
    RosterRepository rosterRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    AtBatRepository atBatRepository;

    @Override
    public AtBat addPlays(final String token,
                            final String gameUuid,
                            final String rosterUuid,
                            final String atBatResultString,
                            final String uniqueId) {

        final User user = getUserFromToken(token);

        final Game game = mustExist(gameRepository.findByUuid(gameUuid));
        final RosterEntry rosterEntry = mustExist(rosterRepository.findByUuid(rosterUuid));

        final AtBat existingAtBat = atBatRepository.findByUniqueIdAndGameIdAndRosterEntryId(uniqueId, game.getId(), rosterEntry.getId());
        final AtBatResult atBatResult = AtBatResult.getByString(atBatResultString);

        if (existingAtBat == null ) {

            // todo ownership validations

            final AtBat atBat = new AtBat();
            atBat.setGame(game);
            atBat.setRosterEntry(rosterEntry);
            atBat.setAtBatResult(atBatResult);
            atBat.setUniqueId(uniqueId);

            return atBatRepository.save(atBat);

        } else {
            existingAtBat.setAtBatResult(atBatResult);
            existingAtBat.setUniqueId(uniqueId);
            return atBatRepository.save(existingAtBat);
        }
    }
}
