package com.drunkensoftball.api.user.service;

import com.drunkensoftball.api.auth.domain.Authentication;
import com.drunkensoftball.api.auth.domain.AuthenticationType;
import com.drunkensoftball.api.auth.repo.AuthenticationRepository;
import com.drunkensoftball.api.service.AbstractService;
import com.drunkensoftball.api.user.domain.User;
import com.drunkensoftball.api.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.UUID;

@Service
public class DefaultUserService extends AbstractService implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationRepository authenticationRepository;


    @Override
    @Transactional
    public User createUser(String username,String displayName, String email) {
        final User user = new User();
        user.setUsername(username);
        user.setDisplayName(isBlank(displayName) ? username : displayName);
        user.setVerified(false);
        user.setInviteSent(false);
        user.setEmail(email);

        return userRepository.save(user);
    }
}
