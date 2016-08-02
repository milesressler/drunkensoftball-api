package com.drunkensoftball.api.auth.repo;

import com.drunkensoftball.api.auth.domain.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

    Authentication findByToken(String token);

}
