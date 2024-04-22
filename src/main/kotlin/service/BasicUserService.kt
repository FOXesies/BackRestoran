package org.example.service

import org.example.entity.Users_.Customer
import org.example.repository.BasicUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BasicUserService {

    @Autowired
    lateinit var basikCustomerRepository: BasicUserRepository

    fun insertUser(customer: Customer){
        basikCustomerRepository.save(customer)
    }
    fun getUser(customerId: Long): Customer {
        return basikCustomerRepository.findById(customerId).get()
    }
}