package com.github.brunodmartins.opengymapi.platform

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionController {

    @ExceptionHandler(RuntimeException::class)
    fun runtimeException(exception: RuntimeException): ResponseEntity<RuntimeException> {
        return ResponseEntity.internalServerError().body(exception);
    }
}