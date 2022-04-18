package com.github.brunodmartins.opengymapi.platform

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.ZonedDateTime
import javax.persistence.EntityNotFoundException

@ControllerAdvice
class ExceptionController : ResponseEntityExceptionHandler() {

    @ExceptionHandler(EntityNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun entityNotFoundException(exception: EntityNotFoundException): ResponseEntity<ErrorMessage> {
        val message = exception.message
        val clazz = message?.substring(message.indexOf(".storage.") + 9, message.indexOf("with id") - 1)
        val id = message?.substring(message.lastIndexOf(" ") + 1)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErrorMessage("Resource not found", "$clazz - $id", HttpStatus.NOT_FOUND.value())
        )
    }

    companion object {
        data class ErrorMessage(val message: String, val error: String?, val status: Int, val timestamp: String = ZonedDateTime.now().toString())
    }
}