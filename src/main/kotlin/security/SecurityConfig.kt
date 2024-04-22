package org.example.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class Security {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http.csrf { obj: AbstractHttpConfigurer<*, *> -> obj.disable() }

            .cors { cors ->
                cors.configurationSource { request ->


                    val corsConfiguration = CorsConfiguration()
                    corsConfiguration.setAllowedOriginPatterns(listOf("*"))
                    corsConfiguration.allowedMethods = listOf(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                    )
                    corsConfiguration.allowedHeaders = listOf("*")
                    corsConfiguration.allowCredentials = true
                    corsConfiguration
                }
            }
            .authorizeHttpRequests { requests ->
                requests.requestMatchers(
                    HttpMethod.GET,
                    "api/v1/**"
                ).permitAll()
                requests.requestMatchers(
                    HttpMethod.POST,
                    "api/v1/**"
                ).permitAll()
                requests.requestMatchers(HttpMethod.GET,
                    "api/v1/me/**"
                ).authenticated()
            }

            .build()
    }

}