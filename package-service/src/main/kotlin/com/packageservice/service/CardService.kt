package com.packageservice.service

import com.packageservice.domain.entity.Card
import com.packageservice.domain.request.CardRequest
import com.packageservice.exception.CardNotFoundException
import com.packageservice.mapper.CardMapper
import com.packageservice.repository.CardRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CardService(

    val benefitService: BenefitService,
    val cardRepository: CardRepository,
    val cardMapper: CardMapper

) {


    fun create(cardRequest: CardRequest): Card {
        return Optional.of(cardRequest)
            .map(cardMapper::toEntity)
            .map(cardRepository::save)
            .get()
    }


    fun findByCardNumber(cardNumber: String): Card {
        return this.cardRepository.findByCardNumber(cardNumber)
            .orElseThrow { CardNotFoundException("Cartão não encontrado. Número do cartão: $cardNumber") }
    }


    fun addBenefits(
        cardNumber: String,
        benefitId: Long
    ): Card {
        return Optional.of(cardNumber)
            .map {
                this.cardMapper.updateCardBenefits(
                    this.findByCardNumber(cardNumber),
                    this.benefitService.findById(benefitId)
                )
            }
            .map(cardRepository::save)
            .get()
    }


}