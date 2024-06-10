package org.example.auth.service

import com.wayplaner.learn_room.home.domain.model.UpdateCityDTO
import jakarta.servlet.http.HttpSession
import org.example.auth.entity.Sessions
import org.example.auth.repository.ERoleCustomerRepository
import org.example.entity.Users.ERole
import org.example.entity.Users_.Users
import org.example.entity.Users_.DTO.ResponseAuth
import org.example.entity.Users_.DTO.SingInRequest
import org.example.entity.Users_.DTO.SingUpRequest
import org.example.entity.Users_.DTO.UserResponse
import org.example.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class AuthService(
) {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var userDetailsServiceIml: UserDetailsServiceIml

    @Autowired
    private lateinit var sessionService: SessionService

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var repositoryERole: ERoleCustomerRepository

    fun getUser(idUser: Long): UserResponse {
        return MapperUtils.mapUserToDTO(userDetailsServiceIml.userService.findById(idUser))
    }

    fun updateCity(city: UpdateCityDTO){
        val users = userDetailsServiceIml.userService.findById(city.idUser!!)
        users.city = city.city
        userDetailsServiceIml.userService.save(users)
    }


    fun singUp(singUpRequest: SingUpRequest, session: HttpSession): ResponseAuth {

        if (userDetailsServiceIml.userService.findByPhone(singUpRequest.phone) != null){
            return ResponseAuth(message = "Такой номер уже зарегестрирован")
        }

        var users = Users()

        users.phone = singUpRequest.phone.replace("+7", "+8")
        users.password = passwordEncoder.encode(singUpRequest.password)
        users.city = singUpRequest.city
        users.name = singUpRequest.name
        users.roles.add(repositoryERole.findById(1).get())

        users = userDetailsServiceIml.userService.save(users)
        var sessions = sessionService.findByPhone(users.phone)

        if (sessions == null) {
            sessions = Sessions(
                sessionId = session.id,
                phone = users.phone,
                lastAccessTime = LocalDateTime.now()
            )
        }

        sessionService.save(sessions)

        return ResponseAuth(userResponse = MapperUtils.mapUserToDTO(users))
    }

    fun singIn(singInRequest: SingInRequest, session: HttpSession): ResponseAuth {

        val users = userDetailsServiceIml.userService.findByPhone(singInRequest.phone)

        if (userDetailsServiceIml.userService.findByPhone(singInRequest.phone) == null){
            return ResponseAuth(message = "Неверный логин или пароль")
        }

        if (!passwordEncoder.matches(singInRequest.password, users!!.password)){
            return ResponseAuth(message = "Неверный логин или пароль")
        }

        var sessions: Sessions? = sessionService.findByPhone(users.phone)

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                singInRequest.phone,
                singInRequest.password,
                null
            )
        )

        if (sessions == null) {
            sessions = Sessions(
                sessionId = session.id,
                phone = singInRequest.phone
            )
        }

        sessionService.save(sessions)

        return ResponseAuth(userResponse = MapperUtils.mapUserToDTO(users))
    }

    fun updateInfo(userResponse: UserResponse): ResponseAuth{
        val user = userDetailsServiceIml.userService.findById(userResponse.profileUUID)
        if (user.phone != userResponse.phone){
            val checkUser = userDetailsServiceIml.userService.findByPhone(userResponse.phone!!)
            if(checkUser != null){
                return ResponseAuth(message = "Номер уже занят")
            }

            user.phone = userResponse.phone
        }
        user.name = userResponse.name

        userDetailsServiceIml.userService.save(user)
        return ResponseAuth()
    }
}