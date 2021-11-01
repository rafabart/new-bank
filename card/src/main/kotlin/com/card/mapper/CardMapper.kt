package com.card.mapper

import com.card.domain.document.Card
import com.card.domain.request.CardRequest
import com.card.domain.request.CardStatusRequest
import com.card.domain.response.CardResponse
import org.springframework.stereotype.Component

@Component
class CardMapper {

    fun ToDocument(cardRequest: CardRequest, newCardNumber: String): Card {
        return Card(
            customerId = cardRequest.customerId,
            cardNumber = newCardNumber
        )
    }


    fun ToResponse(card: Card): CardResponse {
        return CardResponse(
            id = card.id,
            customerId = card.customerId,
            cardNumber = card.cardNumber,
            status = card.status,
            createAt = card.createAt,
            updatedAt = card.updatedAt
        )
    }

    fun updateStatus(cardStatusRequest: CardStatusRequest, card: Card): Card {
        return Card(
            id = card.id,
            customerId = card.customerId,
            cardNumber = card.cardNumber,
            status = cardStatusRequest.status,
            createAt = card.createAt,
            updatedAt = cardStatusRequest.updatedAt,
        )
    }
}
