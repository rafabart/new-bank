package com.packageservice.mapper

import com.packageservice.domain.entity.Benefit
import com.packageservice.domain.entity.Card
import com.packageservice.domain.request.CardRequest
import com.packageservice.domain.response.CardResponse
import org.springframework.stereotype.Component

@Component
class CardMapper {

    fun toEntity(cardRequest: CardRequest): Card {
        return Card(
            cardNumber = cardRequest.cardNumber
        )
    }


    fun toResponse(card: Card): CardResponse {
        return CardResponse(
            id = card.id,
            cardNumber = card.cardNumber,
            benefits = card.benefits,
            createAt = card.createAt,
            updatedAt = card.updatedAt
        )
    }


    fun addCardBenefits(card: Card, benefit: Benefit): Card {
        return Card(
            id = card.id,
            cardNumber = card.cardNumber,
            benefits = card.benefits.plus(benefit).toMutableSet(),
            createAt = card.createAt
        )
    }


    fun removeCardBenefits(card: Card, benefit: Benefit): Card {
        return Card(
            id = card.id,
            cardNumber = card.cardNumber,
            benefits = card.benefits.filter { it != benefit }.toMutableSet(),
            createAt = card.createAt
        )
    }
}