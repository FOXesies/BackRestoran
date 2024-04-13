package org.example.utils.sms

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Component
import org.springframework.web.context.request.ServletWebRequest

@Component
class SmsCodeGenerator {
    fun generate(request: ServletWebRequest): ValidateCode{
        val code = RandomStringUtils.randomNumeric(4)
        return ValidateCode(code, 60)
    }
}