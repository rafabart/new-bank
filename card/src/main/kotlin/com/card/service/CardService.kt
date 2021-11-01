package com.card.service

import com.card.domain.document.Card
import com.card.domain.request.CardRequest
import com.card.domain.request.CardStatusRequest
import com.card.exception.CardNotFound
import com.card.mapper.CardMapper
import com.card.repository.CardRepositoy
import com.card.util.CardNumberGenerator
import org.springframework.stereotype.Service
import java.util.*

@Service
class CardService(

    val cardRepositoy: CardRepositoy,
    val cardMapper: CardMapper

) {


    fun create(cardRequest: CardRequest): Card {

        val newCardNumber = getValidNewCardNumber()

        val card = this.cardMapper.ToDocument(cardRequest, newCardNumber)

        return this.cardRepositoy.insert(card)
    }


    fun findByCardNumber(cardNumber: String): Optional<Card> {
        return Optional.of(cardNumber)
            .map(cardRepositoy::findByCardNumber)
            .orElseThrow { CardNotFound("Cartão não encontrado. Número do Cartão = $cardNumber") }
    }


    fun findById(id: String): Optional<Card> {
        return Optional.of(id)
            .map(cardRepositoy::findById)
            .orElseThrow { CardNotFound("Cartão não encontrado. Id = $id") }
    }


    fun updateStatus(cardStatusRequest: CardStatusRequest): Optional<Card> {
        return Optional.of(cardStatusRequest)
            .map(CardStatusRequest::cardNumber)
            .map(this::findByCardNumber)
            .map { c -> this.cardMapper.updateStatus(cardStatusRequest, c.get()) }
            .map(cardRepositoy::save)
    }


    private fun getValidNewCardNumber(): String {

        var validCardNumber = false
        var newCardNumber = ""

        while (!validCardNumber) {

            newCardNumber = CardNumberGenerator.generate()

            if (this.cardRepositoy.findByCardNumber(newCardNumber).isEmpty)
                validCardNumber = true

        }

        return newCardNumber
    }

}
