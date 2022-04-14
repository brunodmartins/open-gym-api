package com.github.brunodmartins.opengymapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OpenGymApiApplication

fun main(args: Array<String>) {
	runApplication<OpenGymApiApplication>(*args)
}
