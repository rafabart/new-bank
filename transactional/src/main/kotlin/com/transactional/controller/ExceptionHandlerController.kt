package com.transactional.controller

import com.transactional.domain.response.ErrorResponse
import com.transactional.exception.TransactionNotFoundException
import feign.FeignException
import org.json.JSONObject
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandlerController {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TransactionNotFoundException::class)
    fun cardNotFound(
        exception: TransactionNotFoundException,
        request: HttpServletRequest
    ): ErrorResponse {

        return ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FeignException::class)
    fun handleFeignStatusException(
        exception: FeignException,
    ): ErrorResponse {

        val messages = JSONObject(exception.contentUTF8()).toMap()

        return ErrorResponse(
            status = messages["status"].toString().toInt(),
            error = messages["error"].toString(),
            message = messages["message"].toString(),
            path = exception.request().url()
        )
    }
}