package com.drunkensoftball.api.user.service


import com.drunkensoftball.api.user.domain.User
import org.hibernate.validator.constraints.NotBlank
import org.springframework.validation.annotation.Validated

@Validated
interface UserService {

    fun createUser(@NotBlank(message = "Username is required") username: String?,
                   displayName: String?,
                   @NotBlank(message = "Email is required") email: String?): User

    fun deleteUser(uuid: String)

}
