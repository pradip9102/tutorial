package com.vizurd.tutorial.controller

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ErrorController {
    private val logger = KotlinLogging.logger { }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exception(throwable: Throwable? = null, model: Model): String {
        if (throwable == null) {
            logger.error { "Exception during execution of SpringSecurity application" }
        } else {
            logger.error(throwable) { "Exception during execution of SpringSecurity application" }
        }

        val errorMessage = throwable?.message ?: "Unknown error"
        model.addAttribute("errorMessage", errorMessage)
        return "error"
    }
}