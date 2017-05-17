package com.drunkensoftball.api.auth.service;

import com.drunkensoftball.api.auth.basic.domain.BasicAuthentication;
import com.drunkensoftball.api.auth.basic.repo.BasicAuthenticationRepository;
import com.drunkensoftball.api.auth.domain.Authentication;
import com.drunkensoftball.api.auth.google.domain.GoogleAuthentication;
import com.drunkensoftball.api.auth.google.repo.GoogleAuthenticationRepository;
import com.drunkensoftball.api.auth.repo.AuthenticationRepository;
import com.drunkensoftball.api.exception.BadGatewayException;
import com.drunkensoftball.api.exception.NotFoundException;
import com.drunkensoftball.api.user.domain.User;
import com.drunkensoftball.api.user.repo.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class DefaultAuthenticationService implements AuthenticationService {


    private static final String CLIENT_ID_WEB = "301730243353-crulut0j7kem5kdpb9jq9t8prndlknh2.apps.googleusercontent.com";
    private static final String CLIENT_ID_IOS = "301730243353-599r4n4l693ld8rtm8s9b2hh4qpnjihf.apps.googleusercontent.com";
    private static final String INVALID_LOGIN_CREDENTIALS = "Invalid user or password.";

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    GoogleAuthenticationRepository googleAuthenticationRepository;

    @Autowired
    BasicAuthenticationRepository basicAuthenticationRepository;

    @Autowired
    UserRepository userRepository;



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

    @Override
    public Authentication basicAuthentication(final String user, final String password) {
//        final BasicAuthentication basicAuthentication = basicAuthenticationRepository.findByUserUsername(user);
//        if (basicAuthentication != null ) {
//            final String unverified = basicAuthentication.getSalt() + password;
//
//        }
//        else {
//            throw new NotFoundException(INVALID_LOGIN_CREDENTIALS);
//        }
        return null;


    }


    @Override
    public Authentication googleAuthentication(final String googleToken) {
        final GoogleIdToken idToken =  getGoogleIdToken(googleToken);

        if (idToken == null) {
            throw new BadGatewayException("Unable to verify token.");
        } else {
            final GoogleIdToken.Payload payload = idToken.getPayload();
            final String userId = payload.getSubject();

            final GoogleAuthentication googleAuthentication = googleAuthenticationRepository.findByGoogleId(userId).orElseGet(new Supplier<GoogleAuthentication>() {
                @Override
                public GoogleAuthentication get() {
                    final User user = new User();
                    user.setDisplayName((String) payload.get("name"));
                    user.setEmail(payload.getEmail());
                    user.setVerified(payload.getEmailVerified());
                    user.setInviteSent(false);
                    user.setUsername((String) payload.get("given_name") + payload.get("family_name") + System.currentTimeMillis()/1000 );
                    userRepository.save(user);

                    final Authentication authentication = createToken(user);

                    final GoogleAuthentication googleAuthentication = new GoogleAuthentication();
                    googleAuthentication.setGoogleId(userId);
                    googleAuthentication.setAuthentication(authentication);
                    return googleAuthenticationRepository.save(googleAuthentication);
                }
            });
            return googleAuthentication.getAuthentication();
        }
    }

    private GoogleIdToken getGoogleIdToken(String googleToken) {
        try {
            final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance())
                    .setAudience(Arrays.asList(CLIENT_ID_WEB, CLIENT_ID_IOS))
                    // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
                    // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
                    // "accounts.google.com". If you need to verify tokens from multiple sources, build
                    // a GoogleIdTokenVerifier for each issuer and try them both.
                    .setIssuer("accounts.google.com")
                    .build();
            final GoogleIdTokenVerifier httpsVerifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance())
                    .setAudience(Arrays.asList(CLIENT_ID_WEB, CLIENT_ID_IOS))
                    // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
                    // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
                    // "accounts.google.com". If you need to verify tokens from multiple sources, build
                    // a GoogleIdTokenVerifier for each issuer and try them both.
                    .setIssuer("https://accounts.google.com")
                    .build();
            final GoogleIdToken googleIdTokenWeb = verifier.verify(googleToken);
            if (googleIdTokenWeb != null) {
                return googleIdTokenWeb;
            } else {
                return httpsVerifier.verify(googleToken);
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new BadGatewayException("Unable to verify token.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new BadGatewayException("Unable to verify token.");
        }
    }
}
