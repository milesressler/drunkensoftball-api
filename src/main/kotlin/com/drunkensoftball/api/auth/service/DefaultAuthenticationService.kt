package com.drunkensoftball.api.auth.service


import com.drunkensoftball.api.auth.domain.Authentication
import com.drunkensoftball.api.auth.domain.BasicAuthentication
import com.drunkensoftball.api.auth.domain.GoogleAuthentication
import com.drunkensoftball.api.auth.repo.AuthenticationRepository
import com.drunkensoftball.api.auth.repo.BasicAuthenticationRepository
import com.drunkensoftball.api.auth.repo.GoogleAuthenticationRepository
import com.drunkensoftball.api.exception.BadGatewayException
import com.drunkensoftball.api.exception.NonUniqueException
import com.drunkensoftball.api.exception.NotFoundException
import com.drunkensoftball.api.user.domain.User
import com.drunkensoftball.api.user.repo.UserRepository
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

import java.io.IOException
import java.security.GeneralSecurityException
import java.util.Arrays
import java.util.UUID
import javax.transaction.Transactional

@Service
open class DefaultAuthenticationService : AuthenticationService {

    @Autowired
    lateinit var googleAuthenticationRepository: GoogleAuthenticationRepository

    @Autowired
    lateinit var basicAuthenticationRepository: BasicAuthenticationRepository

    @Autowired
    lateinit var authenticationRepository: AuthenticationRepository

    @Autowired
    lateinit var userRepository: UserRepository


    private val logger = LoggerFactory.getLogger(DefaultAuthenticationService::class.java)

    val bCryptPasswordEncoder = BCryptPasswordEncoder()

    override fun getUserFromToken(token: String): User {
        val authentication = authenticationRepository.findByToken(token)
        if (authentication.user == null) {
            throw NotFoundException("User not found")
        } else {
            return authentication.user!!
        }
    }

    override fun loginWithBasicAuthentication(user: String, password: String): Authentication {
        val userEntity = userRepository.findFirstByUsernameOrEmail(user, user).orElseThrow({ NotFoundException("User not found") })
        val basicAuthentication = userEntity.basicAuthentication
        if (basicAuthentication == null) {
            throw NotFoundException("User not found")
        } else if (!isPasswordValid(basicAuthentication.passwordHash!!, password)) {
            throw NotFoundException("User not found")
        } else {
            val authentication = Authentication()
            authentication.token = UUID.randomUUID().toString()
            authentication.user = userEntity
            return authenticationRepository.save(authentication)
        }
    }

    @Transactional
    override fun createNewUserBasicAuthentication(username: String, password: String, email: String, firstName: String?, lastName: String?): Authentication {
        userRepository.findFirstByUsernameOrEmail(username, email).ifPresent {
            val entity = if(it.email == email) "Email" else "Username"
            throw NonUniqueException("$entity already exists")
        }

        // TODO Username and password validations

        var user = User()
        user.username = username
        user.firstName = firstName
        user.lastName = lastName
        user.verified = false
        user.email = email
        user = userRepository.save(user)

        val basicAuthentication = BasicAuthentication()
        basicAuthentication.user = user
        basicAuthentication.passwordHash = hashPassword(password)
        basicAuthenticationRepository.save(basicAuthentication)

        val authentication = Authentication()
        authentication.token = UUID.randomUUID().toString()
        authentication.user = user
        return authenticationRepository.save(authentication)
    }

    private fun hashPassword(password: String): String {
        return bCryptPasswordEncoder.encode(password)
    }

    private fun isPasswordValid(encryptedPassword: String, providedPassword: String): Boolean {
        return bCryptPasswordEncoder.matches(providedPassword, encryptedPassword)
    }

    override fun googleAuthentication(googleToken: String): Authentication {
        val idToken = getGoogleIdToken(googleToken)

        if (idToken == null) {
            throw BadGatewayException("Unable to verify token.")
        } else {
            val payload = idToken.payload
            val userId = payload.subject

            val user = googleAuthenticationRepository.findByGoogleId(userId).orElseGet({
                val user = User()
                user.firstName = payload["given_name"] as String
                user.lastName = payload["family_name"] as String
                user.email = payload.email
                user.verified = payload.emailVerified
                user.username = user.firstName + user.lastName + System.currentTimeMillis() / 1000
                userRepository.save(user)

                val googleAuthentication = GoogleAuthentication()
                googleAuthentication.googleId = userId
                googleAuthentication.user = user
                googleAuthenticationRepository.save(googleAuthentication)
            }).user

            val authentication = Authentication()
            authentication.token = UUID.randomUUID().toString()
            authentication.user = user
            return authenticationRepository.save(authentication)
        }
    }

    private fun getGoogleIdToken(googleToken: String): GoogleIdToken? {
        try {
            val verifier = GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance())
                    .setAudience(Arrays.asList(CLIENT_ID_WEB, CLIENT_ID_IOS))
                    // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
                    // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
                    // "accounts.google.com". If you need to verify tokens from multiple sources, build
                    // a GoogleIdTokenVerifier for each issuer and try them both.
                    .setIssuer("accounts.google.com")
                    .build()
            val httpsVerifier = GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance())
                    .setAudience(Arrays.asList(CLIENT_ID_WEB, CLIENT_ID_IOS))
                    // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
                    // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
                    // "accounts.google.com". If you need to verify tokens from multiple sources, build
                    // a GoogleIdTokenVerifier for each issuer and try them both.
                    .setIssuer("https://accounts.google.com")
                    .build()
            val googleIdTokenWeb = verifier.verify(googleToken)
            return googleIdTokenWeb ?: httpsVerifier.verify(googleToken)
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
            throw BadGatewayException("Unable to verify token.")
        } catch (e: IOException) {
            e.printStackTrace()
            throw BadGatewayException("Unable to verify token.")
        }

    }

    companion object {
        private val CLIENT_ID_WEB = "301730243353-crulut0j7kem5kdpb9jq9t8prndlknh2.apps.googleusercontent.com"
        private val CLIENT_ID_IOS = "301730243353-599r4n4l693ld8rtm8s9b2hh4qpnjihf.apps.googleusercontent.com"
        private val INVALID_LOGIN_CREDENTIALS = "Invalid user or password."
    }
}
