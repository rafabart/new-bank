package com.transactional.controller

import com.transactional.domain.request.TransactionRequest
import com.transactional.domain.response.TransactionResponse
import com.transactional.mapper.TransactionMapper
import com.transactional.service.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("transactions")
class TransactionController(

    val transactionMapper: TransactionMapper,
    val transactionService: TransactionService

) {


    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getTransactionById(@PathVariable id: String): TransactionResponse {

        return Optional.of(id)
            .map(transactionService::findById)
            .map(transactionMapper::toResponse)
            .get()
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getTransactionByCard(@RequestParam cardNumber: String): List<TransactionResponse> {

        return this.transactionService.getAllByCardNumber(cardNumber)
            .map(transactionMapper::toResponse)
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody transactionRequest: TransactionRequest): TransactionResponse {

        return Optional.of(transactionRequest)
            .map(transactionService::create)
            .map(transactionMapper::toResponse)
            .get()
    }
}
