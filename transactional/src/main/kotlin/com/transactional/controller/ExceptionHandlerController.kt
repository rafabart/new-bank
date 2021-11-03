package com.transactional.controller

import com.transactional.domain.response.ErrorResponse
import com.transactional.exception.CardStatusException
import com.transactional.exception.TransactionNotFoundException
import feign.FeignException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandlerController {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TransactionNotFoundException::class)
    fun transactionNotFoundException(
        exception: TransactionNotFoundException,
        request: HttpServletRequest
    ): ErrorResponse {

        return ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.requestURI
        )
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FeignException.NotFound::class)
    fun cardNotFoundException(
        exception: FeignException.NotFound,
    ): ErrorResponse {

        return ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = exception.request().url()
        )
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FeignException.FeignServerException::class)
    fun handleConnectException(
        exception: FeignException.FeignServerException,
    ): ErrorResponse {

        return ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message,
            path = exception.request().url()
        )
    }


    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(CardStatusException::class)
    fun handleCardStatusException(
        exception: CardStatusException,
        request: HttpServletRequest
    ): ErrorResponse {

        return ErrorResponse(
            status = HttpStatus.PRECONDITION_FAILED.value(),
            error = HttpStatus.PRECONDITION_FAILED.name,
            message = exception.message,
            path = request.servletPath
        )
    }
}