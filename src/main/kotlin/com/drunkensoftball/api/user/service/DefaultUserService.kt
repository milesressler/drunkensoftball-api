package com.drunkensoftball.api.user.service

import com.drunkensoftball.api.exception.NonUniqueException
import com.drunkensoftball.api.service.AbstractService
import com.drunkensoftball.api.user.domain.User
import com.drunkensoftball.api.user.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import org.apache.commons.lang3.StringUtils.isBlank

@Service
open class DefaultUserService : AbstractService(), UserService {

    @Autowired
    lateinit var userRepository: UserRepository


    @Transactional
    override fun createUser(username: String?,
                            displayName: String?,
                            email: String?): User {


        var userOptional = userRepository.findFirstByUsernameOrEmail(username!!, email!!)
        if (userOptional.isPresent) {
            if (userOptional.get().email == email) {
                throw NonUniqueException("User already exists")
            } else {
                throw NonUniqueException("Username already exists")
            }
        }

        val user = User()
        user.username = username
        user.displayName = if (isBlank(displayName)) username else displayName
        user.verified = false
        user.inviteSent = false
        user.email = email

        return userRepository.save(user)
    }

    override fun deleteUser(uuid: String) {
        //        final User user = userRepository.findOne()

    }
}
