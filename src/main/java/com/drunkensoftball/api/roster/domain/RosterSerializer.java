package com.drunkensoftball.api.roster.domain;

import com.drunkensoftball.api.support.jackson.BaseEntitySerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class RosterSerializer extends BaseEntitySerializer<RosterEntry> {


    @Override
    public void serialize(final RosterEntry rosterEntry,
                          final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {

        jsonGenerator.writeStartObject();

        // Start User
        writeAbstractEntityFields(rosterEntry, jsonGenerator);

        jsonGenerator.writeNumberField(FIELD_POSITION, rosterEntry.getFieldPosition().id);
        jsonGenerator.writeNumberField(BATTING_POSITION, rosterEntry.getBattingPosition());

        jsonGenerator.writeObjectFieldStart(USER);
        writeAbstractEntityFields(rosterEntry.getUser(), jsonGenerator);
        jsonGenerator.writeStringField(DISPAY_NAME, rosterEntry.getUser().getDisplayName());
        jsonGenerator.writeStringField(USERNAME, rosterEntry.getUser().getUsername());
        jsonGenerator.writeEndObject();

        jsonGenerator.writeObjectFieldStart(TEAM);
        writeAbstractEntityFields(rosterEntry.getTeam(), jsonGenerator);
        jsonGenerator.writeStringField(NAME, rosterEntry.getTeam().getName());
        jsonGenerator.writeEndObject();

        // End User
        jsonGenerator.writeEndObject();
    }
}
