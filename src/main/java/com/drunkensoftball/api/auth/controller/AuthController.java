package com.drunkensoftball.api.auth.controller;

import com.drunkensoftball.api.auth.basic.domain.BasicAuthentication;
import com.drunkensoftball.api.auth.basic.domain.BasicAuthenticationRequest;
import com.drunkensoftball.api.auth.domain.Authentication;
import com.drunkensoftball.api.auth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;
    private static final String URL_AUTH = "/auth";
    private static final String URL_GOOGLE = "/google";
    private static final String URL_BASIC = "/basic";
    private static final String URL_FACEBOOK = "/facebook";

    @RequestMapping(value = URL_AUTH + URL_GOOGLE, method = RequestMethod.POST)
    public Authentication googleAuth(@RequestHeader(value = "Authorization") String idTokenString) {
        return authenticationService.googleAuthentication(idTokenString);
    }



    @RequestMapping(value = URL_AUTH + URL_BASIC, method = RequestMethod.POST)
    public Authentication basicAuth(@RequestBody BasicAuthenticationRequest basicAuthenticationRequest) {
//        return authenticationService.googleAuthentication(idTokenString);
        return null;
    }


    @RequestMapping(value = URL_AUTH + URL_FACEBOOK, method = RequestMethod.POST)
    public Authentication facebookAuth(@RequestHeader(value = "Authorization") String facebookTokenString) {
//        return authenticationService.googleAuthentication(idTokenString);
        return null;
    }

}
