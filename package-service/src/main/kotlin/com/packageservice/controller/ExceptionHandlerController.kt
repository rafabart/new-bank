package com.packageservice.controller

import com.packageservice.domain.response.ErrorResponse
import com.packageservice.exception.BenefitNotFoundException
import com.packageservice.exception.CardNotFoundException
import com.packageservice.exception.RepeatedBenefitOnCardException
import de.jupf.staticlog.Log
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandlerController {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BenefitNotFoundException::class)
    fun handleBenefitNotFoundException(
        exception: BenefitNotFoundException,
        request: HttpServletRequest
    ): ErrorResponse {

        exception.message?.let { Log.warn(it) }

        return ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.requestURI
        )
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CardNotFoundException::class)
    fun handleCardNotFoundException(
        exception: CardNotFoundException,
        request: HttpServletRequest
    ): ErrorResponse {

        exception.message?.let { Log.warn(it) }

        return ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.requestURI
        )
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RepeatedBenefitOnCardException::class)
    fun handleRepeatedBenefitOnCardException(
        exception: RepeatedBenefitOnCardException,
        request: HttpServletRequest
    ): ErrorResponse {

        exception.message?.let { Log.warn(it) }

        return ErrorResponse(
            status = HttpStatus.CONFLICT.value(),
            error = HttpStatus.CONFLICT.name,
            message = exception.message,
            path = request.servletPath
        )
    }
}