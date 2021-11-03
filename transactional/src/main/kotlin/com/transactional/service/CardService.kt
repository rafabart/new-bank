package com.transactional.service

import com.transactional.client.CardClient
import com.transactional.domain.enum.CardStatus
import com.transactional.domain.response.CardClientResponse
import de.jupf.staticlog.Log
import feign.FeignException
import org.springframework.stereotype.Service
import java.util.*

@Service
class CardService(

    val cardClient: CardClient

) {


    fun isValidCard(cardNumber: String): Boolean {

        try {
            return Optional.of(cardNumber)
                .map(cardClient::findByCardNumber)
                .map(CardClientResponse::status)
                .filter { s -> s == CardStatus.ACTIVATED }
                .isPresent

        } catch (e: FeignException.NotFound) {
            Log.warn("Cartão não encontrado. Número do Cartão = $cardNumber")

            throw FeignException.NotFound(
                "Cartão não encontrado. Número do Cartão = $cardNumber",
                e.request(),
                e.request().body()
            )

        } catch (e: FeignException) {
            Log.error("Conexão recusada com card-api")

            throw FeignException.FeignServerException(
                e.status(),
                "Conexão recusada com card-api",
                e.request(),
                e.request().body()
            )
        }
    }
}