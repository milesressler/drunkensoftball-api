package com.drunkensoftball.api.user.domain

import com.drunkensoftball.api.support.jackson.BaseEntitySerializer
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider

import java.io.IOException

class UserSerializer : BaseEntitySerializer<User>() {


    @Throws(IOException::class)
    override fun serialize(entity: User,
                           jsonGenerator: JsonGenerator,
                           provider: SerializerProvider) {

        jsonGenerator.writeStartObject()

        // Start User
        writeAbstractEntityFields(entity, jsonGenerator)
        jsonGenerator.writeStringField(BaseEntitySerializer.DISPLAY_NAME, entity.displayName() )
        jsonGenerator.writeStringField(BaseEntitySerializer.FIRST_NAME, entity.firstName)
        jsonGenerator.writeStringField(BaseEntitySerializer.LAST_NAME, entity.lastName)
        jsonGenerator.writeStringField(BaseEntitySerializer.USERNAME, entity.username)
        jsonGenerator.writeStringField(BaseEntitySerializer.EMAIL, entity.email)

        jsonGenerator.writeArrayFieldStart(BaseEntitySerializer.JOINED_TEAMS)
        if (entity.teams != null) {
            for (t in entity.teams!!) {
                jsonGenerator.writeStartObject()
                writeAbstractEntityFields(t.team!!, jsonGenerator)
                jsonGenerator.writeStringField(BaseEntitySerializer.NAME, t.team?.name)
                jsonGenerator.writeEndObject()
            }
        }
        jsonGenerator.writeEndArray()

        jsonGenerator.writeArrayFieldStart(BaseEntitySerializer.MANAGED_TEAMS)
        if (entity.managedTeams != null) {
            for (t in entity.managedTeams!!) {
                jsonGenerator.writeStartObject()
                writeAbstractEntityFields(t, jsonGenerator)
                jsonGenerator.writeStringField(BaseEntitySerializer.NAME, t.name)
                jsonGenerator.writeEndObject()
            }
        }
        jsonGenerator.writeEndArray()


        // End User
        jsonGenerator.writeEndObject()
    }
}
