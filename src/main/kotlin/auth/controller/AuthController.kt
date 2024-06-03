package org.example.auth.controller

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import org.example.auth.service.AuthService
import org.example.entity.Users_.DTO.ResponseAuth
import org.example.entity.Users_.DTO.SingInRequest
import org.example.entity.Users_.DTO.SingUpRequest
import org.example.entity.Users_.DTO.UserResponse
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/auth")
class AuthController(private val authService: AuthService) {
    @RequestMapping(value = ["/sing-up"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun singUp(@RequestBody singUpRequest: SingUpRequest, session: HttpSession?): ResponseAuth {
        return authService.singUp(singUpRequest, session!!)
    }

    @RequestMapping(value = ["/sing-in"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun singIn(@RequestBody singInRequest: SingInRequest, session: HttpSession?): ResponseAuth {
        return authService.singIn(singInRequest, session!!)
    }

    @RequestMapping(value = ["/logout"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun logout(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            val cookies: Array<out Cookie>? = request.cookies
            if (cookies != null) {
                for (cookie in cookies) {
                    cookie.setValue(null)
                    cookie.setPath("/")
                    cookie.setMaxAge(0)
                    response.addCookie(cookie)
                }
            }
            request.session.invalidate()
            SecurityContextHolder.getContext().authentication = null
        } catch (ex: Exception) {
            println("auth logout: " + ex.message)
        }
    }
}