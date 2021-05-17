package edu.pasudo123.study.demo.index

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("index")
class IndexController {

    @GetMapping
    fun index(): ResponseEntity<String> {
        return ResponseEntity.ok("index")
    }
}