package com.drunkensoftball.api.user.service

import org.springframework.validation.annotation.Validated

@Validated
interface UserService {

    fun deleteUser(uuid: String)

}
