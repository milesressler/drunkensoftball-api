package com.drunkensoftball.api.team.domain;

import com.drunkensoftball.api.roster.domain.Roster;
import com.drunkensoftball.api.support.jackson.BaseEntitySerializer;
import com.drunkensoftball.api.user.domain.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class TeamSerializer extends BaseEntitySerializer<Team> {

    private static final String NAME = "name";
    private static final String MANAGER = "manager";
    private static final String PLAYERS = "players";

    @Override
    public void serialize(final Team team,
                          final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {

        jsonGenerator.writeStartObject();

        // Start User
        writeAbstractEntityFields(team, jsonGenerator);
        jsonGenerator.writeStringField(NAME, team.getName());
        jsonGenerator.writeObjectField(MANAGER, team.getManager());
        if (team.getPlayers() != null){
            jsonGenerator.writeArrayFieldStart(PLAYERS);
            for (Roster roster : team.getPlayers()){
                jsonGenerator.writeObject(roster.getUser());
            }
            jsonGenerator.writeEndArray();
        }

        // End User
        jsonGenerator.writeEndObject();
    }
}
