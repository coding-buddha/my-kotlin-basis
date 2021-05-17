package edu.pasudo123.study.demo.customer

import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class CustomerService {

    @PostConstruct
    fun init() {

    }

    fun getCustomerById(id: Long): Customer {
        TODO("리턴해야함")
    }

    init {

    }
}