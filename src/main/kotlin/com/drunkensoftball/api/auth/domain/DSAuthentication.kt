package com.drunkensoftball.api.auth.domain

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class DSAuthentication: Authentication {

    var authenticationEntity: AuthenticationEntity? = null

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        if (!isAuthenticated) {
            authenticationEntity = null
        }
    }

    override fun getName(): String? {
        return authenticationEntity?.user?.uuid
    }

    override fun getCredentials(): Any? {
        return authenticationEntity?.token
    }

    override fun getPrincipal(): Any? {
        return authenticationEntity?.user
    }

    override fun isAuthenticated(): Boolean {
        return authenticationEntity != null
    }

    override fun getDetails(): Any? {
        var detailMap: Map<String, Any> = HashMap()
        return detailMap
    }
}