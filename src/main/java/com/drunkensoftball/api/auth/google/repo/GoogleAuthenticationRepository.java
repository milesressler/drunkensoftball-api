package com.drunkensoftball.api.auth.google.repo;

import com.drunkensoftball.api.auth.google.domain.GoogleAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoogleAuthenticationRepository extends JpaRepository<GoogleAuthentication, Long> {

    Optional<GoogleAuthentication> findByGoogleId(String googleId);

}
