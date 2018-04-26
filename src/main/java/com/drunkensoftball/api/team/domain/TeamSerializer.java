package com.drunkensoftball.api.team.domain;

import com.drunkensoftball.api.roster.domain.RosterEntry;
import com.drunkensoftball.api.support.jackson.BaseEntitySerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class TeamSerializer extends BaseEntitySerializer<Team> {

    @Override
    public void serialize(final Team team,
                          final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {

        jsonGenerator.writeStartObject();

        // Start User
        writeAbstractEntityFields(team, jsonGenerator);
        jsonGenerator.writeStringField(NAME, team.getName());



        jsonGenerator.writeObjectFieldStart(MANAGER);
        writeAbstractEntityFields(team.getManager(), jsonGenerator);
        jsonGenerator.writeEndObject();

        if (team.getPlayers() != null){
            jsonGenerator.writeArrayFieldStart(ROSTER);
            for (final RosterEntry rosterEntry : team.getPlayers()){
                writeAbstractEntityFields(rosterEntry, jsonGenerator);
                jsonGenerator.writeNumberField(FIELD_POSITION, rosterEntry.getFieldPosition().id);
                jsonGenerator.writeNumberField(BATTING_POSITION, rosterEntry.getBattingPosition());
            }
            jsonGenerator.writeEndArray();
        }

        // End User
        jsonGenerator.writeEndObject();
    }
}
