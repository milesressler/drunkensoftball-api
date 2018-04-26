package com.drunkensoftball.api.user.domain

import com.drunkensoftball.api.support.jackson.BaseEntitySerializer
import com.drunkensoftball.api.team.domain.Team
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider

import java.io.IOException

class UserSerializer : BaseEntitySerializer<User>() {


    @Throws(IOException::class)
    override fun serialize(user: User,
                           jsonGenerator: JsonGenerator,
                           provider: SerializerProvider) {

        jsonGenerator.writeStartObject()

        // Start User
        writeAbstractEntityFields(user, jsonGenerator)
        jsonGenerator.writeStringField(BaseEntitySerializer.DISPAY_NAME, user.displayName)
        jsonGenerator.writeStringField(BaseEntitySerializer.USERNAME, user.username)

        jsonGenerator.writeArrayFieldStart(BaseEntitySerializer.JOINED_TEAMS)
        if (user.teams != null) {
            for (t in user.teams!!) {
                jsonGenerator.writeStartObject()
                writeAbstractEntityFields<Team>(t.team, jsonGenerator)
                jsonGenerator.writeStringField(BaseEntitySerializer.NAME, t.team.name)
                jsonGenerator.writeEndObject()
            }
        }
        jsonGenerator.writeEndArray()

        jsonGenerator.writeArrayFieldStart(BaseEntitySerializer.MANAGED_TEAMS)
        if (user.managedTeams != null) {
            for (t in user.managedTeams!!) {
                jsonGenerator.writeStartObject()
                writeAbstractEntityFields<Team>(t, jsonGenerator)
                jsonGenerator.writeStringField(BaseEntitySerializer.NAME, t.name)
                jsonGenerator.writeEndObject()
            }
        }
        jsonGenerator.writeEndArray()


        // End User
        jsonGenerator.writeEndObject()
    }
}
