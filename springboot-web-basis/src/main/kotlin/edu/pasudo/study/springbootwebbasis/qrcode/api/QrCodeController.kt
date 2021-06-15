package edu.pasudo.study.springbootwebbasis.qrcode.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/qr")
class QrCodeController {

    @PostMapping
    fun generateQrCode(): ResponseEntity<String> {
        return ResponseEntity("qr-code", HttpStatus.OK)
    }
}