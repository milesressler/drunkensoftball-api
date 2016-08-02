package com.drunkensoftball.api.auth.domain;

import com.drunkensoftball.api.domain.BaseEntity;
import com.drunkensoftball.api.user.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "authentication")
public class Authentication extends BaseEntity {

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user", nullable = false)
    private User user;

    @Column(name="token", nullable = false)
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
