package edu.pasudo.study.springbootwebbasis.employee.api

import edu.pasudo.study.springbootwebbasis.employee.model.Employee
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("employee")
class EmployeeController {

    @GetMapping
    fun getEmployee() : ResponseEntity<Employee> {
        Thread.sleep(500)
        return ResponseEntity.ok(Employee.create())
    }
}