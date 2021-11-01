package com.transactional.service

import com.transactional.client.CardClient
import com.transactional.domain.enum.CardStatus
import com.transactional.domain.response.CardClientResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class CardService(

    val cardClient: CardClient

) {


    fun isValidCard(cardNumber: String): Boolean {

        return Optional.of(cardNumber)
            .map(cardClient::findByCardNumber)
            .map(CardClientResponse::status)
            //TODO: Tentar colocar uma regra aqui para verificar o status do cartão e lançar uma excessão caso não esteja ativo
            .filter { s -> s == CardStatus.ACTIVATED }
            .isPresent
    }
}