package com.drunkensoftball.api.team.domain


import com.drunkensoftball.api.support.jackson.BaseEntitySerializer
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider

import java.io.IOException

class TeamSerializer : BaseEntitySerializer<Team>() {

    @Throws(IOException::class)
    override fun serialize(entity: Team,
                           jsonGenerator: JsonGenerator,
                           provider: SerializerProvider) {

        jsonGenerator.writeStartObject()

        // Start User
        writeAbstractEntityFields(entity, jsonGenerator)
        jsonGenerator.writeStringField(BaseEntitySerializer.NAME, entity.name)

        jsonGenerator.writeObjectFieldStart(BaseEntitySerializer.MANAGER)
            writeAbstractEntityFields(entity.manager!!, jsonGenerator)
            jsonGenerator.writeStringField(BaseEntitySerializer.DISPLAY_NAME, entity.manager?.displayName() )
            jsonGenerator.writeStringField(BaseEntitySerializer.FIRST_NAME, entity.manager?.firstName)
            jsonGenerator.writeStringField(BaseEntitySerializer.LAST_NAME, entity.manager?.lastName)
            jsonGenerator.writeStringField(BaseEntitySerializer.USERNAME, entity.manager?.username)
        jsonGenerator.writeEndObject()

        jsonGenerator.writeArrayFieldStart(BaseEntitySerializer.ROSTER)
            entity.players?.forEach {
                jsonGenerator.writeStartObject()
                writeAbstractEntityFields(it, jsonGenerator)
                jsonGenerator.writeObjectField(BaseEntitySerializer.FIELD_POSITION, it.fieldPosition?.name)
                jsonGenerator.writeObjectField(BaseEntitySerializer.BATTING_POSITION, it.battingPosition)
                jsonGenerator.writeObjectFieldStart(USER)
                    writeAbstractEntityFields(it.user!!, jsonGenerator)
                    jsonGenerator.writeStringField(BaseEntitySerializer.DISPLAY_NAME, it.user?.displayName() )
                    jsonGenerator.writeStringField(BaseEntitySerializer.FIRST_NAME, it.user?.firstName)
                    jsonGenerator.writeStringField(BaseEntitySerializer.LAST_NAME, it.user?.lastName)
                    jsonGenerator.writeStringField(BaseEntitySerializer.USERNAME, it.user?.username)
                jsonGenerator.writeEndObject()
                jsonGenerator.writeEndObject()
            }
        jsonGenerator.writeEndArray()

        // End User
        jsonGenerator.writeEndObject()
    }
}
