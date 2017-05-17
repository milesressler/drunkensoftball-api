package com.drunkensoftball.api.auth.basic.domain;

import com.drunkensoftball.api.auth.domain.Authentication;
import com.drunkensoftball.api.domain.BaseEntity;
import com.drunkensoftball.api.user.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "basic_authentication")
public class BasicAuthentication  extends BaseEntity {


    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="authentication", nullable = false)
    private Authentication authentication;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="user", nullable = false)
    private User user;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "salt")
    private String salt;

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
