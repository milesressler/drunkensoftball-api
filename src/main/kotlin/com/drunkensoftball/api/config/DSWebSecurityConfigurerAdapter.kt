package com.drunkensoftball.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
open class DSWebSecurityConfigurerAdapter: WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        (httpSecurity.authorizeRequests().anyRequest() as ExpressionUrlAuthorizationConfigurer.AuthorizedUrl).permitAll()
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        httpSecurity.csrf().disable()
    }
}