package com.drunkensoftball.api.auth.google.domain;

import com.drunkensoftball.api.auth.domain.Authentication;
import com.drunkensoftball.api.domain.BaseEntity;
import com.drunkensoftball.api.user.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "google_authentication")
public class GoogleAuthentication extends BaseEntity {

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="authentication", nullable = false)
    private Authentication authentication;

    @Column(name="google_id", nullable = false)
    private String googleId;

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }
}
