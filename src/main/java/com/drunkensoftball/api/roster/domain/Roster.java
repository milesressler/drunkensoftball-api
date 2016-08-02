package com.drunkensoftball.api.roster.domain;

import com.drunkensoftball.api.domain.BaseEntity;
import com.drunkensoftball.api.team.domain.Team;
import com.drunkensoftball.api.user.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "roster")
public class Roster extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "team", nullable = false)
    private Team team;

    @Column(name = "batting_position")
    private Integer battingPosition;

    @Column(name = "field_position")
    private FieldPosition fieldPosition;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getBattingPosition() {
        return battingPosition;
    }

    public void setBattingPosition(int battingPosition) {
        this.battingPosition = battingPosition;
    }

    public FieldPosition getFieldPosition() {
        return fieldPosition;
    }

    public void setFieldPosition(FieldPosition fieldPosition) {
        this.fieldPosition = fieldPosition;
    }
}
