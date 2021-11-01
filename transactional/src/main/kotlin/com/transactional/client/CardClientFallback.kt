package com.transactional.client

import com.transactional.domain.response.CardClientResponse
import com.transactional.exception.CardNotFoundException

class CardClientFallback : CardClient {

    override fun findByCardNumber(cardNumber: String): CardClientResponse {
        throw CardNotFoundException("Cartão não encontrado. Número do Cartão = $cardNumber")
    }

    //TODO: Fazer o fallback funcionar
}