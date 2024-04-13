package org.example.utils.sms

import java.time.LocalDateTime



class ValidateCode(code: String, i: Int) {
    private var code: String? = null

    private var expireTime: LocalDateTime? = null

    fun ValidateCode(code: String?, expireIn: Int) {
        this.code = code
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn.toLong())
    }

    fun ValidateCode(code: String?, expireTime: LocalDateTime?) {
        this.code = code
        this.expireTime = expireTime
    }

    fun isExpried(): Boolean {
        return LocalDateTime.now().isAfter(expireTime)
    }

    fun getCode(): String? {
        return code
    }

    fun setCode(code: String?) {
        this.code = code
    }

    fun getExpireTime(): LocalDateTime? {
        return expireTime
    }

    fun setExpireTime(expireTime: LocalDateTime?) {
        this.expireTime = expireTime
    }

}