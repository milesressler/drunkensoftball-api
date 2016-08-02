package com.drunkensoftball.api.team.domain;


import com.drunkensoftball.api.domain.BaseEntity;
import com.drunkensoftball.api.roster.domain.Roster;
import com.drunkensoftball.api.user.domain.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team")
@JsonSerialize(using = TeamSerializer.class)
public class Team extends BaseEntity{

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "manager")
    private User manager;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Roster> players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public List<Roster> getPlayers() {
        return players;
    }

    public void setPlayers(List<Roster> players) {
        this.players = players;
    }

    public Team() {
    }
}
