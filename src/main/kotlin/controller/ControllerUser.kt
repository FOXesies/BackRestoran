package org.example.controller

import jakarta.mail.MessagingException
import jakarta.servlet.http.HttpServletRequest
import org.example.entity.users.Users
import org.example.service.UserSerice
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.io.UnsupportedEncodingException


@RestController
@RequestMapping("api/v1/auth")
class ControllerUser {

    @Autowired
    lateinit var serviceUser: UserSerice

    @RequestMapping(path = ["/verify"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun verifyUser(@Param("code") code: String?): String {
        return if (serviceUser.verify(code)) {
            "verify_success"
        } else {
            "verify_fail"
        }
    }

    @RequestMapping(path = ["/process_register"], method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Throws(UnsupportedEncodingException::class, MessagingException::class)
    fun processRegister(
        @RequestParam(name = "name") name: String,
        @RequestParam(name = "phone") phone: String,
        @RequestParam(name = "mail") mail: String, request: HttpServletRequest): String {

        val user = Users(name = name, phone = phone, mail = mail, verificationCode = null, enabled = false)
        serviceUser.register(user, getSiteURL(request))
        return "register_success"
    }

    private fun getSiteURL(request: HttpServletRequest): String {
        val siteURL = "http://localhost:8080/api/v1/auth"
        return siteURL
    }
}