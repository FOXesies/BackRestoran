package org.example.security

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.auth.entity.Sessions
import org.example.auth.service.SessionService
import org.example.auth.service.UserDetailsServiceIml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*


@Component
class SessionFilter: OncePerRequestFilter() {

    @Autowired
    private lateinit var sessionService: SessionService

    @Autowired
    private lateinit var userDetailsServiceIml: UserDetailsServiceIml

    //@Override
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("Авторизация")
        // Получаем массив куки
        val cookies: Array<Cookie>? = request.cookies

        // Ищем куки с именем "JSESSIONID"
        var cookieSessions: Cookie? = null
        if (cookies != null) {
            cookieSessions = Arrays.stream(cookies)
                .filter { x -> x.getName().equals("JSESSIONID") }
                .findFirst()
                .orElse(null)
        }

        var sessions: Sessions? = null
        val requestSessions = request.getSession(false)


        if (cookieSessions != null) {
            sessions = sessionService.findBySessionsId(cookieSessions.getValue())
            System.out.println("КУКА " + cookieSessions.getValue())
            if (requestSessions != null) println("SESSIONS " + requestSessions.id)
        }

        if (SecurityContextHolder.getContext().authentication != null) println("NOT NULL KENT")
        // Если сессия найдена и пользователь еще не аутентифицирован
        if (sessions != null && SecurityContextHolder.getContext().authentication == null) {
            try {
                // Загружаем UserDetails по имени пользователя из сессии
                val userDetails = userDetailsServiceIml.loadUserByUsername(sessions.phone!!)

                // Создаем контекст безопасности и устанавливаем в него аутентификацию
                val context = SecurityContextHolder.createEmptyContext()
                val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails!!.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                context.authentication = authToken
                SecurityContextHolder.setContext(context)
            } catch (ex: Exception) {
                println(ex.message)
            } finally {
                // Устанавливаем атрибуты куки
                cookieSessions!!.setHttpOnly(true)
                cookieSessions.setSecure(true)
                response.addCookie(cookieSessions)
            }
        }


        filterChain.doFilter(request, response)
    }
}