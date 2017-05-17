package com.drunkensoftball.api.roster.domain;

import com.drunkensoftball.api.support.jackson.BaseEntitySerializer;
import com.drunkensoftball.api.user.domain.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class RosterSerializer extends BaseEntitySerializer<RosterEntry> {

    private static final String USER = "user";

    @Override
    public void serialize(final RosterEntry rosterEntry,
                          final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {

        jsonGenerator.writeStartObject();

        // Start User
        writeAbstractEntityFields(rosterEntry, jsonGenerator);
        jsonGenerator.writeObjectField(USER, rosterEntry.getUser());

        // End User
        jsonGenerator.writeEndObject();
    }
}
