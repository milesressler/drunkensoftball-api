package com.drunkensoftball.api.user.service

import com.drunkensoftball.api.service.AbstractService
import com.drunkensoftball.api.user.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class DefaultUserService : AbstractService(), UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun deleteUser(uuid: String) {
        //        final User user = userRepository.findOne()

    }
}
