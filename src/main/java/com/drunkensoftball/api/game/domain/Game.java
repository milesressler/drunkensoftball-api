package com.drunkensoftball.api.game.domain;

import com.drunkensoftball.api.domain.BaseEntity;
import com.drunkensoftball.api.team.domain.Team;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game")
//@JsonSerialize(using = GameSerializer.class)
public class Game extends BaseEntity{

    @ManyToOne(optional = false)
    @JoinColumn(name = "team")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
