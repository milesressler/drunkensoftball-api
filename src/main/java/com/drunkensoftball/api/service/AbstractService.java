package com.drunkensoftball.api.service;

import com.drunkensoftball.api.auth.repo.AuthenticationRepository;
import com.drunkensoftball.api.exception.NotFoundException;
import com.drunkensoftball.api.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService {

    @Autowired
    AuthenticationRepository authenticationRepository;

    public <T> T mustExist(T o){
        if (o == null){
            throw new NotFoundException("Can't find entity");
        } else {
            return o;
        }
    }

    public User getUserFromToken(final String token) {
        return mustExist(authenticationRepository.findByToken(token).getUser());
    }

}
