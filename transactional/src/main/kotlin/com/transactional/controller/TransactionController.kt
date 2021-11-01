package com.transactional.controller

import com.transactional.domain.request.TransactionRequest
import com.transactional.domain.response.TransactionResponse
import com.transactional.mapper.TransactionMapper
import com.transactional.service.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("transactions")
class TransactionController(

    val transactionMapper: TransactionMapper,
    val transactionService: TransactionService

) {


    @GetMapping("{id}")
    fun getTransactionById(@PathVariable id: String): TransactionResponse {

        val transaction = this.transactionService.findById(id)

        return this.transactionMapper.toResponse(transaction)
    }


    @GetMapping
    fun getTransactionByCard(@RequestParam cardNumber: String): List<TransactionResponse> {

        return this.transactionService.getAllByCardNumber(cardNumber)
            .map(transactionMapper::toResponse)
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody transactionRequest: TransactionRequest): TransactionResponse {

        val response = this.transactionService.create(transactionRequest)

        return this.transactionMapper.toResponse(response)
    }
}