package com.drunkensoftball.api.roster.domain


import com.drunkensoftball.api.support.jackson.BaseEntitySerializer
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider

import java.io.IOException

class RosterSerializer : BaseEntitySerializer<RosterEntry>() {


    @Throws(IOException::class)
    override fun serialize(entity: RosterEntry,
                           jsonGenerator: JsonGenerator,
                           provider: SerializerProvider) {

        jsonGenerator.writeStartObject()

        // Start User
        writeAbstractEntityFields(entity, jsonGenerator)

        jsonGenerator.writeObjectField(BaseEntitySerializer.FIELD_POSITION, entity.fieldPosition?.name)
        jsonGenerator.writeObjectField(BaseEntitySerializer.BATTING_POSITION, entity.battingPosition)

        jsonGenerator.writeObjectFieldStart(BaseEntitySerializer.USER)
        writeAbstractEntityFields(entity.user!!, jsonGenerator)
        jsonGenerator.writeStringField(BaseEntitySerializer.FIRST_NAME, entity.user!!.firstName)
        jsonGenerator.writeStringField(BaseEntitySerializer.LAST_NAME, entity.user!!.lastName)
        jsonGenerator.writeStringField(BaseEntitySerializer.USERNAME, entity.user!!.username)
        jsonGenerator.writeEndObject()

        jsonGenerator.writeObjectFieldStart(BaseEntitySerializer.TEAM)
        writeAbstractEntityFields(entity.team!!, jsonGenerator)
        jsonGenerator.writeStringField(BaseEntitySerializer.NAME, entity.team!!.name)
        jsonGenerator.writeEndObject()

        // End User
        jsonGenerator.writeEndObject()
    }
}
