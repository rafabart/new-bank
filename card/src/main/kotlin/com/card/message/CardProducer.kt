package com.card.message

import com.card.domain.document.Card
import com.card.exception.MessageCardNotSentException
import com.card.util.Log
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.util.*

@Component
class CardProducer(

    val streamBridge: StreamBridge

) {

    companion object : Log() {
        const val CARD_TOPIC_OUT = "card-topic"
    }


    fun send(card: Card): Boolean {

        return Optional.of(card)
            .map { MessageBuilder.withPayload(card) }
            .map {
                it.setHeader(
                    KafkaHeaders.MESSAGE_KEY,
                    card.cardNumber.toByteArray(Charsets.UTF_8)
                )
            }
            .map { it.build() }
            .map { this.streamBridge.send(CARD_TOPIC_OUT, it) }
            .orElseThrow {
                logger.warn("[CardProducer][send]Falha ao enviar a mensagem[$card] para o binder: $CARD_TOPIC_OUT.")
                MessageCardNotSentException("Falha ao enviar a mensagem[$card] para o binder: $CARD_TOPIC_OUT.")
            }
    }
}
