package com.drunkensoftball.api.team.service;

import com.drunkensoftball.api.team.domain.Team;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface TeamService {

    Team createTeam(@NotBlank(message = "Authentication token required") String token,
                    @NotBlank(message = "Team name required.") String name);

    List<Team> getTeams(@NotBlank(message = "Authentication token required") String token,
                        Pageable pageable);

    Team getTeam(@NotBlank(message = "Authentication token required") String token,
                 @NotBlank(message = "Team uuid required") String teamUuid);
}
