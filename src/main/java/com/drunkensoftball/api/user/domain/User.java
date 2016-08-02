package com.drunkensoftball.api.user.domain;

import com.drunkensoftball.api.domain.BaseEntity;
import com.drunkensoftball.api.roster.domain.Roster;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@JsonSerialize(using = UserSerializer.class)
public class User extends BaseEntity {

    @Column(name="email")
    private String email;

    @Column(name="username", unique = true, nullable = false)
    private String username;

    @Column(name="display_name")
    private String displayName;

    @Column(name="verified", nullable = false)
    private Boolean verified;

    @Column(name="invite_sent", nullable = false)
    private Boolean inviteSent;

    @OneToMany(mappedBy = "user")
    private List<Roster> teams;

//    @ManyToMany(mappedBy = "players")
//    private List<Team> teams;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean getInviteSent() {
        return inviteSent;
    }

    public void setInviteSent(Boolean inviteSent) {
        this.inviteSent = inviteSent;
    }

    public List<Roster> getTeams() {
        return teams;
    }

    public User() {
    }
}
