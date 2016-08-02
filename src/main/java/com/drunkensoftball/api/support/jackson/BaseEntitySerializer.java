package com.drunkensoftball.api.support.jackson;

import com.drunkensoftball.api.domain.BaseEntity;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class BaseEntitySerializer<T extends BaseEntity>  extends JsonSerializer<T> {

    private static final String CREATED = "created";
    private static final String UPDATED = "updated";
    protected static final String ID = "id";
    protected static final String UUID = "uuid";

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

    protected void writeAbstractEntityFields(final T t, final JsonGenerator jsonGenerator) throws IOException {
        jsonGenerator.writeStringField(UUID, t.getUuid());
        this.handleCreateUpdateFields(t, jsonGenerator);
    }

    protected void handleCreateUpdateFields(final T t, final JsonGenerator jsonGenerator) throws IOException {
        if (null != t.getCreated()) {
            jsonGenerator.writeNumberField(CREATED, t.getCreated().getTimeInMillis());
        }

        if (null != t.getUpdated()) {
            jsonGenerator.writeNumberField(UPDATED, t.getUpdated().getTimeInMillis());
        }
    }
}
