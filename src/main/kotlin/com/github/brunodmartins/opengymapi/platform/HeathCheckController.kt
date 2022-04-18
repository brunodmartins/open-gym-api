package com.github.brunodmartins.opengymapi.platform

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["Heath check"])
@RestController
@RequestMapping("/ping")
class HeathCheckController {

    @GetMapping
    fun ping() = "pong"
}