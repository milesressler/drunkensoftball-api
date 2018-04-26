package com.drunkensoftball.api.auth.domain;

public enum AuthenticationType {

    password(0),
    google(2);

    public int id;

    AuthenticationType(int id) {
        this.id = id;
    }

    public static AuthenticationType getByString(String authenticationTypeString){
        for (AuthenticationType authenticationType : AuthenticationType.values()){
            if (authenticationType.name().equals(authenticationTypeString)){
                return  authenticationType;
            }
        }
        throw new RuntimeException("Authentication Type Not Found");
    }
}
