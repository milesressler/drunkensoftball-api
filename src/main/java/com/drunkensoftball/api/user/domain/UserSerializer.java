package com.drunkensoftball.api.user.domain;

import com.drunkensoftball.api.support.jackson.BaseEntitySerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserSerializer extends BaseEntitySerializer<User> {

    private static final String DISPAY_NAME = "displayName";
    private static final String USERNAME = "username";

    @Override
    public void serialize(final User user,
                          final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {

        jsonGenerator.writeStartObject();

        // Start User
        writeAbstractEntityFields(user, jsonGenerator);
        jsonGenerator.writeStringField(DISPAY_NAME, user.getDisplayName());
        jsonGenerator.writeStringField(USERNAME, user.getUsername());

        // End User
        jsonGenerator.writeEndObject();
    }
}
