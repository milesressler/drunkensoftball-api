package com.drunkensoftball.api.auth.service;

import com.drunkensoftball.api.auth.domain.Authentication;
import com.drunkensoftball.api.user.domain.User;

public interface AuthenticationService {

    User getUserFromToken(String token);

    Authentication createToken(User user);

    Authentication googleAuthentication(String googleToken);

    Authentication basicAuthentication(String user, String password);


}
