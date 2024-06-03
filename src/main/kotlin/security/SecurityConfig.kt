package org.example.security

import org.example.auth.service.UserDetailsServiceIml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration


@Configuration
@EnableWebSecurity
class Security {

    @Autowired
    private lateinit var userDetailsServiceImpl: UserDetailsServiceIml

    @Autowired
    private lateinit var sessionFilter: SessionFilter

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
            .sessionManagement { sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .sessionFixation { it.newSession() }
                    .maximumSessions(1)
            }
            .logout{
                it.logoutSuccessUrl("/api/v1/auth/logout");
                it.deleteCookies("JSESSIONID");
                it.clearAuthentication(true);
                it.invalidateHttpSession(true);
            }
            .addFilterBefore(sessionFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authenticationProvider(daoAuthenticationProvider())
            .build()
    }

    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val daoAuthenticationProvider =
            DaoAuthenticationProvider()
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService())
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        return daoAuthenticationProvider
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder(12)
    }

    @Bean
    fun customUserDetailsService(): UserDetailsService? {
        return userDetailsServiceImpl
    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun authenticationManager(
        config: AuthenticationConfiguration
    ): AuthenticationManager {
        return config.authenticationManager
    }

    @Bean
    fun corsConfiguration(): CorsConfiguration {
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
        return corsConfiguration
    }

}