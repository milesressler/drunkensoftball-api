package com.drunkensoftball.api.auth.service;

import com.drunkensoftball.api.auth.domain.Authentication;
import com.drunkensoftball.api.auth.repo.AuthenticationRepository;
import com.drunkensoftball.api.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Override
    public User getUserFromToken(String token) {
        final Authentication authentication = authenticationRepository.findByToken(token);
        return authentication.getUser();
    }

    @Override
    public Authentication createToken(User user) {

        final Authentication authentication = new Authentication();
        authentication.setToken(UUID.randomUUID().toString());
        authentication.setUser(user);

        return authenticationRepository.save(authentication);
    }
}
