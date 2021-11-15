package com.packageservice.controller

import com.packageservice.domain.request.CardRequest
import com.packageservice.domain.response.CardResponse
import com.packageservice.mapper.CardMapper
import com.packageservice.service.CardService
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpStatus
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@Profile("dev")
@RequestMapping("cards")
class CardController(

    val cardMapper: CardMapper,
    val cardService: CardService,
    val streamBridge: StreamBridge

) {

    companion object {
        const val CARD_TOPIC_OUT = "card-topic"
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody cardRequest: CardRequest): CardResponse {
        return Optional.of(cardRequest)
            .map(cardService::create)
            .map(cardMapper::toResponse)
            .get()
    }


    @PostMapping("kafka")
    @ResponseStatus(HttpStatus.CREATED)
    fun sendToKafka(@RequestBody cardRequest: CardRequest) {
        Optional.of(cardRequest)
            .map { MessageBuilder.withPayload(cardRequest) }
            .map {
                it.setHeader(
                    KafkaHeaders.MESSAGE_KEY,
                    cardRequest.cardNumber.toByteArray(Charsets.UTF_8)
                )
            }
            .map { it.build() }
            .map { this.streamBridge.send(CARD_TOPIC_OUT, it) }

    }


    @GetMapping("{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    fun findByCardNumber(@PathVariable cardNumber: String): CardResponse {
        return Optional.of(cardNumber)
            .map(cardService::findByCardNumber)
            .map(cardMapper::toResponse)
            .get()
    }


    @PutMapping("addCardBenefits/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    fun addCardBenefits(
        @PathVariable cardNumber: String,
        @RequestBody benefitId: Long
    ): CardResponse {
        return Optional.of(cardNumber)
            .map { this.cardService.addCardBenefits(cardNumber, benefitId) }
            .map(cardMapper::toResponse)
            .get()
    }


    @PutMapping("removeCardBenefits/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    fun removeCardBenefits(
        @PathVariable cardNumber: String,
        @RequestBody benefitId: Long
    ): CardResponse {
        return Optional.of(cardNumber)
            .map { this.cardService.removeCardBenefits(cardNumber, benefitId) }
            .map(cardMapper::toResponse)
            .get()
    }
}