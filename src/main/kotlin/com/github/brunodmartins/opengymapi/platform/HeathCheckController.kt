package com.github.brunodmartins.opengymapi.platform

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ping")
class HeathCheckController {

    @GetMapping
    fun ping() = "pong"
}