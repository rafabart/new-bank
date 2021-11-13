package com.packageservice.message

import com.packageservice.domain.request.CardRequest
import com.packageservice.service.CardService
import com.packageservice.util.Log
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Consumer

@Component
class CardConsumer(

    val cardService: CardService

) {

    companion object : Log()

    @Bean
    fun cardTopic(): Consumer<CardRequest> = Consumer { card ->

        if (this.cardService.hasCardNumberInDatabase(card.cardNumber)) {
            logger.info("[CardConsumer][cardTopic]: Cartão já cadastrado na base. Número do cartão: ${card.cardNumber}")
            return@Consumer
        }

        Optional.of(card)
            .map(cardService::create)
    }
}