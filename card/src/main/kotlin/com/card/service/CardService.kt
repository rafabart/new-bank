package com.card.service

import com.card.domain.document.Card
import com.card.domain.request.CardRequest
import com.card.domain.request.CardStatusRequest
import com.card.exception.CardNotFoundException
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


    fun findByCardNumber(cardNumber: String): Card {
        return this.cardRepositoy.findByCardNumber(cardNumber)
            .orElseThrow { CardNotFoundException("Cartão não encontrado. Número do Cartão = $cardNumber") }
    }


    fun findById(id: String): Card {
        return this.cardRepositoy.findById(id)
            .orElseThrow { CardNotFoundException("Cartão não encontrado. Id = $id") }
    }


    fun updateStatus(cardStatusRequest: CardStatusRequest): Card {
        return Optional.of(cardStatusRequest)
            .map(CardStatusRequest::cardNumber)
            .map(this::findByCardNumber)
            .map { c -> this.cardMapper.updateStatus(cardStatusRequest, c) }
            .map(cardRepositoy::save)
            .get()
    }


    private fun getValidNewCardNumber(): String {

        val newCardNumber = CardNumberGenerator.generate()

        if (this.cardRepositoy.findByCardNumber(newCardNumber).isPresent)
            return getValidNewCardNumber()

        return newCardNumber
    }
}
