package com.card.controller

import com.card.domain.request.CardRequest
import com.card.domain.request.CardStatusRequest
import com.card.domain.response.CardResponse
import com.card.mapper.CardMapper
import com.card.service.CardService
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
            .map(cardMapper::ToResponse)
            .get()
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findByCardNumber(@RequestParam cardNumber: String): CardResponse {
        return Optional.of(cardNumber)
            .map(cardService::findByCardNumber)
            .map(cardMapper::ToResponse)
            .get()
    }


    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: String): CardResponse {
        return Optional.of(id)
            .map(cardService::findById)
            .map(cardMapper::ToResponse)
            .get()
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    fun updateStatus(@RequestBody cardStatusRequest: CardStatusRequest): CardResponse {
        return Optional.of(cardStatusRequest)
            .map(cardService::updateStatus)
            .map(cardMapper::ToResponse)
            .get()
    }
}