package com.card.controller

import com.card.domain.request.CardRequest
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
}