package com.drunkensoftball.api.support.jackson


import com.drunkensoftball.api.domain.BaseEntity
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

import java.io.IOException

open class BaseEntitySerializer<T : BaseEntity> : JsonSerializer<T>() {

    @Throws(IOException::class)
    override fun serialize(entity: T,
                           jsonGenerator: JsonGenerator,
                           provider: SerializerProvider) {

        jsonGenerator.writeStartObject()
        writeAbstractEntityFields(entity, jsonGenerator)

        this.handleSubclass(entity, jsonGenerator)

        jsonGenerator.writeEndObject()
    }

    @Throws(IOException::class)
    fun handleSubclass(t: T, jsonGenerator: JsonGenerator) {
    }

    @Throws(IOException::class)
    fun <U : BaseEntity> writeAbstractEntityFields(u: U, jsonGenerator: JsonGenerator) {
        jsonGenerator.writeStringField(UUID, u.uuid)
        this.handleCreateUpdateFields(u, jsonGenerator)
    }

    @Throws(IOException::class)
    fun <U : BaseEntity> handleCreateUpdateFields(u: U, jsonGenerator: JsonGenerator) {
        if (null != u.created) {
            jsonGenerator.writeNumberField(CREATED, u.created!!.timeInMillis)
        }

        if (null != u.updated) {
            jsonGenerator.writeNumberField(UPDATED, u.updated!!.timeInMillis)
        }
    }

    companion object {

        const val CREATED = "created"
        const val UPDATED = "updated"
        const val ID = "id"
        const val UUID = "uuid"

        const val DISPLAY_NAME = "displayName"
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val USERNAME = "username"
        const val EMAIL = "email"
        const val MANAGED_TEAMS = "managedTeams"
        const val JOINED_TEAMS = "joinedTeams"
        const val USER = "user"
        const val TOKEN = "token"
        const val TEAM = "team"
        const val NAME = "name"
        const val MANAGER = "manager"
        const val ROSTER = "roster"
        const val GAMES = "games"
        const val OPPONENT_NAME = "opponentName"
        const val BATTING_POSITION = "battingPosition"
        const val FIELD_POSITION = "fieldPosition"
    }
}