package com.packageservice.controller

import com.packageservice.domain.request.CardRequest
import com.packageservice.domain.response.CardResponse
import com.packageservice.mapper.CardMapper
import com.packageservice.service.CardService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("cards")
class CardController(

    val cardMapper: CardMapper,
    val cardService: CardService

) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody cardRequest: CardRequest): CardResponse {
        return Optional.of(cardRequest)
            .map(cardService::create)
            .map(cardMapper::toResponse)
            .get()
    }


    @GetMapping("{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    fun findByCardNumber(@PathVariable cardNumber: String): CardResponse {
        return Optional.of(cardNumber)
            .map(cardService::findByCardNumber)
            .map(cardMapper::toResponse)
            .get()
    }


    @PutMapping("{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCardBenefits(
        @PathVariable cardNumber: String,
        @RequestBody benefitId: Long
    ): CardResponse {
        return Optional.of(cardNumber)
            .map { this.cardService.addBenefits(cardNumber, benefitId) }
            .map(cardMapper::toResponse)
            .get()
    }
}