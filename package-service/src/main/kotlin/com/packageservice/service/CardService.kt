package com.packageservice.service

import com.packageservice.domain.entity.Card
import com.packageservice.domain.request.CardRequest
import com.packageservice.exception.CardNotFoundException
import com.packageservice.exception.RepeatedBenefitOnCardException
import com.packageservice.mapper.CardMapper
import com.packageservice.repository.CardRepository
import com.packageservice.util.Log
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CardService(

    val benefitService: BenefitService,
    val cardRepository: CardRepository,
    val cardMapper: CardMapper

) {
    companion object : Log()


    @Transactional
    fun create(cardRequest: CardRequest): Card {
        return Optional.of(cardRequest)
            .map(cardMapper::toEntity)
            .map(cardRepository::save)
            .get()
    }


    fun findByCardNumber(cardNumber: String): Card {
        return this.cardRepository.findByCardNumber(cardNumber)
            .orElseThrow {
                logger.info("[CardService][findByCardNumber]: Cartão não encontrado. Número do cartão: $cardNumber")
                CardNotFoundException("Cartão não encontrado. Número do cartão: $cardNumber")
            }
    }


    fun hasCardNumberInDatabase(cardNumber: String): Boolean {
        return this.cardRepository.findByCardNumber(cardNumber)
            .isPresent
    }


    @Transactional
    fun addCardBenefits(
        cardNumber: String,
        benefitId: Long
    ): Card {

        if (this.findByCardNumber(cardNumber).benefits.any { it.id == benefitId }) {
            logger.info("[CardService][addCardBenefits]: Benefício (id = $benefitId) já presente no cartão (Número do cartão = $cardNumber)")
            throw RepeatedBenefitOnCardException("Benefício (id = $benefitId) já presente no cartão (Número do cartão = $cardNumber)")
        }

        return Optional.of(cardNumber)
            .map {
                this.cardMapper.addCardBenefits(
                    this.findByCardNumber(cardNumber),
                    this.benefitService.findById(benefitId)
                )
            }
            .map(cardRepository::save)
            .get()
    }


    fun removeCardBenefits(
        cardNumber: String,
        benefitId: Long
    ): Card {

        if (this.findByCardNumber(cardNumber).benefits.none { it.id == benefitId }) {
            logger.info("[CardService][removeCardBenefits]: Benefício (id = $benefitId) não presente no cartão (Número do cartão = $cardNumber)")
            throw RepeatedBenefitOnCardException("Benefício (id = $benefitId) não presente no cartão (Número do cartão = $cardNumber)")
        }

        return Optional.of(cardNumber)
            .map {
                this.cardMapper.removeCardBenefits(
                    this.findByCardNumber(cardNumber),
                    this.benefitService.findById(benefitId)
                )
            }
            .map(cardRepository::save)
            .get()
    }
}
