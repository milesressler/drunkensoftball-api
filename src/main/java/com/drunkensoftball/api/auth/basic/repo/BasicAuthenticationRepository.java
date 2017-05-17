package com.drunkensoftball.api.auth.basic.repo;

import com.drunkensoftball.api.auth.basic.domain.BasicAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicAuthenticationRepository  extends JpaRepository<BasicAuthentication, Long> {

    BasicAuthentication findByUserUsername(String username);
}
