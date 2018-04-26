package com.drunkensoftball.api.support.jackson;

import com.drunkensoftball.api.domain.BaseEntity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BaseEntitySerializer<T extends BaseEntity>  extends JsonSerializer<T> {

    protected static final String CREATED = "created";
    protected static final String UPDATED = "updated";
    protected static final String ID = "id";
    protected static final String UUID = "uuid";

    protected static final String DISPAY_NAME = "displayName";
    protected static final String USERNAME = "username";
    protected static final String MANAGED_TEAMS = "managedTeams";
    protected static final String JOINED_TEAMS = "joinedTeams";
    protected static final String USER = "user";
    protected static final String TEAM = "team";
    protected static final String NAME = "name";
    protected static final String MANAGER = "manager";
    protected static final String ROSTER = "roster";
    protected static final String BATTING_POSITION = "battingPosition";
    protected static final String FIELD_POSITION = "fieldPosition";

    @Override
    public void serialize(final T t,
                          final JsonGenerator jsonGenerator,
                          final SerializerProvider provider) throws IOException {

        jsonGenerator.writeStartObject();
        writeAbstractEntityFields(t, jsonGenerator);

        this.handleSubclass(t, jsonGenerator);

        jsonGenerator.writeEndObject();
    }

    protected void handleSubclass(final T t, final JsonGenerator jsonGenerator) throws IOException {
    }

    protected <U extends BaseEntity> void writeAbstractEntityFields(final U u, final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStringField(UUID, u.getUuid());
        this.handleCreateUpdateFields(u, jsonGenerator);
    }

    protected <U extends BaseEntity> void handleCreateUpdateFields(final U u, final JsonGenerator jsonGenerator) throws IOException {
        if (null != u.getCreated()) {
            jsonGenerator.writeNumberField(CREATED, u.getCreated().getTimeInMillis());
        }

        if (null != u.getUpdated()) {
            jsonGenerator.writeNumberField(UPDATED, u.getUpdated().getTimeInMillis());
        }
    }
}
