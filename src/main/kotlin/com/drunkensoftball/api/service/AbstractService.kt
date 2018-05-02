package com.drunkensoftball.api.service

import com.drunkensoftball.api.exception.NotFoundException

abstract class AbstractService {

    fun <T> mustExist(o: T?): T {
        return o ?: throw NotFoundException("Can't find entity")
    }
}
