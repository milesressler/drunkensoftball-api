package com.drunkensoftball.api.auth.domain

import com.drunkensoftball.api.support.jackson.BaseEntitySerializer
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider

import java.io.IOException

class AuthenticationSerializer : BaseEntitySerializer<Authentication>() {


    @Throws(IOException::class)
    override fun serialize(entity: Authentication,
                           jsonGenerator: JsonGenerator,
                           provider: SerializerProvider) {

        jsonGenerator.writeStartObject()

        jsonGenerator.writeStringField(TOKEN, entity.token)
        jsonGenerator.writeObjectField(USER, entity.user)

        jsonGenerator.writeEndObject()
    }
}
