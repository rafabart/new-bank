package com.customer.service

import com.customer.client.CardClient
import com.customer.domain.entity.Customer
import com.customer.domain.request.CardClientRequest
import com.customer.exception.CardGenerationFailException
import com.customer.util.Log
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class CardService(

    private val cardClient: CardClient

) {

    companion object : Log()


    fun createCard(customer: Customer): Customer {
        return Optional.of(customer)
            .map {
                CardClientRequest(
                    customerId = customer.id.toString()
                )
            }
            .map(cardClient::createCard)
            .filter { it.statusCode == HttpStatus.CREATED }
            .map {
                logger.info("[CardService][createCard]:Cartão criado com sucesso. Número do cartão: ${it.body!!.cardNumber}")
            }
            .map { customer }
            .orElseThrow {
                logger.info("[CardService][createCard]: Não foi possível gerar o novo cartão. CLiente = $customer")
                CardGenerationFailException("Não foi possível gerar o novo cartão. CLiente = $customer")
            }

    }
}
