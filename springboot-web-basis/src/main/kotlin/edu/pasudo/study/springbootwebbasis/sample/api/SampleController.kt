package edu.pasudo.study.springbootwebbasis.sample.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
@RequestMapping("/sample")
class SampleController {

    /**
     * 명시적으로 header 값을 응답에 포함시킬 수 있다.
     */
    @GetMapping
    fun getSampleIncludeHeader(): ResponseEntity<String> {
        val headers = LinkedMultiValueMap<String, String>().apply {
            add("userId", Random.nextLong(10000, 99999).toString())
        }
        return ResponseEntity("hello world", headers, HttpStatus.OK)
    }
}