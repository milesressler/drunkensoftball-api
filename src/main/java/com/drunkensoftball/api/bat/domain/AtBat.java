package com.drunkensoftball.api.bat.domain;


import com.drunkensoftball.api.domain.BaseEntity;
import com.drunkensoftball.api.game.domain.Game;
import com.drunkensoftball.api.roster.domain.RosterEntry;

import javax.persistence.*;

@Entity
@Table(name = "bat")
public class AtBat extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "rosterEntry", nullable = false)
    private RosterEntry rosterEntry;

    @ManyToOne
    @JoinColumn(name = "game", nullable = false)
    private Game game;

    @Column(name = "unique_id")
    private String uniqueId;

    @Column(name = "result")
    private AtBatResult atBatResult;

    public RosterEntry getRosterEntry() {
        return rosterEntry;
    }

    public void setRosterEntry(RosterEntry rosterEntry) {
        this.rosterEntry = rosterEntry;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public AtBatResult getAtBatResult() {
        return atBatResult;
    }

    public void setAtBatResult(AtBatResult atBatResult) {
        this.atBatResult = atBatResult;
    }
}
