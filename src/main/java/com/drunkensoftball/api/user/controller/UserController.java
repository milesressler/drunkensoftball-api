package com.drunkensoftball.api.user.controller;

import com.drunkensoftball.api.auth.domain.AuthenticationType;
import com.drunkensoftball.api.auth.service.AuthenticationService;
import com.drunkensoftball.api.user.domain.User;
import com.drunkensoftball.api.user.domain.UserRequest;
import com.drunkensoftball.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    private static final String URL_USER = "/user";

    @RequestMapping(value = URL_USER, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody final UserRequest userRequest) {

        final User user = userService.createUser(userRequest.getUsername(), null, userRequest.getEmail());

        final AuthenticationType authenticationType = AuthenticationType.getByString(userRequest.getAuthenticationType());
        return authenticationService.createToken(user).getToken();
    }

//    @RequestMapping(value = URL_USER, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public User getUser() {
//        return userServic
//    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String healthCheck() {
        return "Success";
    }

}
