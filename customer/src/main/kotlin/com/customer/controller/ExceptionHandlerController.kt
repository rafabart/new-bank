package com.customer.controller

import com.customer.domain.response.ErrorResponse
import com.customer.exception.CustomerNotFoundException
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper
import org.springframework.dao.DataIntegrityViolationException
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
}