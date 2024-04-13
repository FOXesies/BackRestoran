package org.example.service

import jakarta.mail.MessagingException
import net.bytebuddy.utility.RandomString
import org.example.entity.users.Users
import org.example.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.io.UnsupportedEncodingException


@Service
class UserSerice {

    @Autowired
    lateinit var userRepository: UserRepository


    fun getUserById(id: Long): Users {
        return userRepository.findById(id).get()
    }

    @Autowired
    lateinit var mailSender: JavaMailSender

    @Throws(UnsupportedEncodingException::class, MessagingException::class)
    fun register(user: Users, siteURL: String?) {

        val randomCode = RandomString.make(64)
        user.verificationCode = (randomCode)
        user.enabled = (false)

        userRepository.save(user)

        sendVerificationEmail(user, siteURL!!)
    }

    @Throws(MessagingException::class, UnsupportedEncodingException::class)
    private fun sendVerificationEmail(user: Users, siteURL: String) {
        val toAddress: String = user.mail!!
        val fromAddress = "dostavka_diplom@mail.ru"
        val senderName = "dostavka"
        val subject = "Please verify your registration"
        var content = ("Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">[[URL]]</a></h3>"
                + "Thank you,<br>"
                + "Your company name.")

        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message)

        helper.setFrom(fromAddress, senderName)
        helper.setTo(toAddress)
        helper.setSubject(subject)

        content = content.replace("[[name]]", user.name!!)
        val verifyURL = siteURL + "/verify?code=" + user.verificationCode

        content = content.replace("[[URL]]", verifyURL)

        helper.setText(content, true)

        mailSender.send(message)
    }

    fun verify(verificationCode: String?): Boolean {
        val user: Users? = userRepository.findByVerificationCode(verificationCode)

        if (user == null || user.enabled) {
            return false
        } else {
            user.verificationCode = null
            user.enabled = true
            userRepository.save(user)

            return true
        }
    }
}