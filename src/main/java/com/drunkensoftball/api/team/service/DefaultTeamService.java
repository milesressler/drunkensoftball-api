package com.drunkensoftball.api.team.service;

import com.drunkensoftball.api.auth.repo.AuthenticationRepository;
import com.drunkensoftball.api.roster.domain.RosterEntry;
import com.drunkensoftball.api.roster.repo.RosterRepository;
import com.drunkensoftball.api.service.AbstractService;
import com.drunkensoftball.api.team.domain.Team;
import com.drunkensoftball.api.team.repo.TeamRepository;
import com.drunkensoftball.api.user.domain.User;
import com.drunkensoftball.api.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class DefaultTeamService extends AbstractService implements TeamService {

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    RosterRepository rosterRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Team createTeam(final String token,
                           final String name) {

        final User user = mustExist(authenticationRepository.findByToken(token)).getUser();
        final Team team = new Team();
        team.setName(name);
        team.setManager(user);

        final RosterEntry rosterEntry =  new RosterEntry();
        rosterEntry.setTeam(team);
        rosterEntry.setUser(user);

        final List<RosterEntry> playerList = new LinkedList<RosterEntry>();
        playerList.add(rosterEntry);
        team.setPlayers(playerList);

        return teamRepository.save(team);
    }

    @Override
    public List<Team> getTeams(final String token,
                               final Pageable pageable) {

        final User manager = mustExist(authenticationRepository.findByToken(token)).getUser();
        return teamRepository.findByManagerId(manager.getId(), pageable);
    }

    @Override
    public Team getTeam(final String token,
                        final String teamUuid) {

        final User manager = mustExist(authenticationRepository.findByToken(token)).getUser();
        return mustExist(teamRepository.findByUuidAndManagerId(teamUuid, manager.getId()));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void deleteTeam(final String token,
                           final String teamUuid) {

        final User manager = mustExist(authenticationRepository.findByToken(token)).getUser();
        final Team team =  mustExist(teamRepository.findByUuidAndManagerId(teamUuid, manager.getId()));

        for (RosterEntry rosterEntry : rosterRepository.findByTeamId(team.getId())) {

            final int numberOfTeams = rosterRepository.findByUserId(rosterEntry.getUser().getId()).size();

            rosterEntry.setRemoved(true);
            rosterRepository.save(rosterEntry);

            if (numberOfTeams <= 1) {
                rosterEntry.getUser().setRemoved(true);
                userRepository.save(rosterEntry.getUser());
                // TODO use user service isntead
            }

        }
        team.setRemoved(true);
        teamRepository.save(team);


    }
}
