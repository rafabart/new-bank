package com.packageservice.message

import com.packageservice.domain.request.CardRequest
import com.packageservice.service.CardService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Consumer

@Component
class CardConsumer(

    val cardService: CardService

) {

    companion object {
        const val CARD_TOPIC_OUT = "card-topic"
    }


    @Bean
    fun cardTopic(): Consumer<CardRequest> = Consumer { card ->
        Optional.of(card)
            .map(CardRequest::cardNumber)
            .map(cardService::findByCardNumber)
            .ifPresentOrElse(
                card -> { println(card) },
        card -> this.cardService.create(card)

        )
//            .map(cardService::cardServicecreate)
        //TODO: Criar um log decente aqui
//            .map { println(it) }
        //TODO: Verificar se já existe na base o cartão antes de salvar
    }
}