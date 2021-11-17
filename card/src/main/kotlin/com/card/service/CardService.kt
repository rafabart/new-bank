package com.card.service

import com.card.domain.document.Card
import com.card.domain.request.CardRequest
import com.card.domain.request.CardStatusRequest
import com.card.exception.CardNotFoundException
import com.card.mapper.CardMapper
import com.card.message.CardProducer
import com.card.repository.CardRepositoy
import com.card.util.CardNumberGenerator
import com.card.util.Log
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CardService(

    val cardRepositoy: CardRepositoy,
    val cardProducer: CardProducer,
    val cardMapper: CardMapper

) {

    companion object : Log()


    @Transactional
    fun create(cardRequest: CardRequest): Card {

        val newCardNumber = getValidNewCardNumber()

        val card = this.cardMapper.ToDocument(cardRequest, newCardNumber)

        val newSavedCard = this.cardRepositoy.insert(card)

        if (this.cardProducer.send(newSavedCard))
            logger.info("[CardService][create]: Novo cartão persistido com sucesso. Número do cartão: ${card.cardNumber}")

        return newSavedCard
    }


    fun findByCardNumber(cardNumber: String): Card {
        return this.cardRepositoy.findByCardNumber(cardNumber)
            .orElseThrow {
                logger.info("[CardService][findByCardNumber]: Cartão não encontrado. Número do Cartão = $cardNumber")
                CardNotFoundException("Cartão não encontrado. Número do Cartão = $cardNumber")
            }
    }


    fun findById(id: String): Card {
        return this.cardRepositoy.findById(id)
            .orElseThrow {
                logger.info("[CardService][findById]: Cartão não encontrado. Id = $id")
                CardNotFoundException("Cartão não encontrado. Id = $id")
            }
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
