package com.drunkensoftball.api.config

import com.drunkensoftball.api.auth.service.AuthenticationInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@EnableWebMvc
open class DrunkenMvcConfigurerAdapter: WebMvcConfigurerAdapter() {

    @Autowired
    lateinit var authenticationInterceptor: AuthenticationInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authenticationInterceptor)
        super.addInterceptors(registry)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowedOrigins("*")

        super.addCorsMappings(registry)
    }
}