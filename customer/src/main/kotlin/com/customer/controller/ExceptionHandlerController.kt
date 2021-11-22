package com.customer.controller

import com.customer.domain.response.ErrorResponse
import com.customer.exception.CardGenerationFailException
import com.customer.exception.CustomerNotFoundException
import feign.RetryableException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLException
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandlerController {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException::class)
    fun handleCardNotFoundException(
        exception: CustomerNotFoundException,
        request: HttpServletRequest
    ): ErrorResponse {

        return ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.requestURI
        )
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SQLException::class)
    fun handleDuplicateEmailFoundException(
        exception: SQLException,
        request: HttpServletRequest
    ): ErrorResponse {

        return ErrorResponse(
            status = HttpStatus.CONFLICT.value(),
            error = HttpStatus.CONFLICT.name,
            message = exception.message,
            path = request.requestURI
        )
    }


    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(CardGenerationFailException::class)
    fun handleCardGenerationFailException(
        exception: CardGenerationFailException,
        request: HttpServletRequest
    ): ErrorResponse {

        return ErrorResponse(
            status = HttpStatus.SERVICE_UNAVAILABLE.value(),
            error = HttpStatus.SERVICE_UNAVAILABLE.name,
            message = exception.message,
            path = request.requestURI
        )
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(RetryableException::class)
    fun handleRetryableException(
        exception: RetryableException,
        request: HttpServletRequest
    ): ErrorResponse {

        return ErrorResponse(
            status = HttpStatus.SERVICE_UNAVAILABLE.value(),
            error = HttpStatus.SERVICE_UNAVAILABLE.name,
            message = exception.message,
            path = request.requestURI
        )
    }
}