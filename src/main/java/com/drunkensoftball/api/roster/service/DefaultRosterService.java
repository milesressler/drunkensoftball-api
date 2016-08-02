package com.drunkensoftball.api.roster.service;


import com.drunkensoftball.api.roster.domain.FieldPosition;
import com.drunkensoftball.api.roster.domain.Roster;
import com.drunkensoftball.api.roster.repo.RosterRepository;
import com.drunkensoftball.api.service.AbstractService;
import com.drunkensoftball.api.team.domain.Team;
import com.drunkensoftball.api.team.repo.TeamRepository;
import com.drunkensoftball.api.user.domain.User;
import com.drunkensoftball.api.user.service.UserService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class DefaultRosterService extends AbstractService implements RosterService {

    @Autowired
    RosterRepository rosterRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserService userService;

    @Override
    public Roster addPlayerByUuid(final String token,
                                  final String teamUuid,
                                  final String playerUuid,
                                  final Integer battingPosition,
                                  final String fieldPosition) {
        return null;
    }

    @Override
    public Roster addGuestPlayer(final String token,
                                 final String teamUuid,
                                 final String displayName,
                                 final Integer battingPosition,
                                 final String fieldPositionString) {

        final User user = getUserFromToken(token);
        final Team team = teamRepository.findByUuidAndManagerId(teamUuid, user.getId());
        final User guestPlayer = userService.createUser(StringUtils.delete(UUID.randomUUID().toString(), "-" ), displayName, null);

        final Roster roster = new Roster();
        roster.setTeam(team);
        roster.setUser(guestPlayer);
        roster.setBattingPosition(battingPosition);

        if (isNotBlank(fieldPositionString)){
            final FieldPosition fieldPosition = FieldPosition.getByString(fieldPositionString);
            roster.setFieldPosition(fieldPosition);
        }

        return rosterRepository.save(roster);
    }
}
