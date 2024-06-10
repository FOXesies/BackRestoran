package org.example.auth.controller

import com.wayplaner.learn_room.home.domain.model.UpdateCityDTO
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
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/auth")
class AuthController(private val authService: AuthService) {
    @RequestMapping(value = ["/sing-up"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun singUp(@RequestBody singUpRequest: SingUpRequest, session: HttpSession?): ResponseAuth {
        return authService.singUp(singUpRequest, session!!)
    }
    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUser(@PathVariable(value = "id") idUser: Long): UserResponse {
        return authService.getUser(idUser)
    }

    @RequestMapping(value = ["/update"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUser(@RequestBody response: UserResponse): ResponseAuth{
        return authService.updateInfo(response)
    }

    @RequestMapping(value = ["/sing-in"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun singIn(@RequestBody singInRequest: SingInRequest, session: HttpSession?): ResponseAuth {
        return authService.singIn(singInRequest, session!!)
    }

    @RequestMapping(value = ["/update_city"], method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateCity(@RequestBody city: UpdateCityDTO) {
        return authService.updateCity(city)
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