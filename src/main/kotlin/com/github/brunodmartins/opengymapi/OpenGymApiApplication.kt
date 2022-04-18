package com.github.brunodmartins.opengymapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
class OpenGymApiApplication

fun main(args: Array<String>) {
	runApplication<OpenGymApiApplication>(*args)
}
