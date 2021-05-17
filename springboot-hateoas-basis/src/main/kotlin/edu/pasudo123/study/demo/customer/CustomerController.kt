package edu.pasudo123.study.demo.customer

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @GetMapping("{id}")
    fun getCustomerById(@PathVariable id: Long): ResponseEntity<Customer> {
        return ResponseEntity.ok(customerService.getCustomerById(id))
    }
}