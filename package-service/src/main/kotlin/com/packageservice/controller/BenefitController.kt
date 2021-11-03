package com.packageservice.controller

import com.packageservice.BenefitService
import com.packageservice.domain.request.BenefitRequest
import com.packageservice.domain.response.BenefitResponse
import com.packageservice.mapper.BenefitMapper
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("benefit")
class BenefitController(

    val benefitMapper: BenefitMapper,
    val benefitService: BenefitService

) {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody benefitRequest: BenefitRequest): BenefitResponse {
        return Optional.of(benefitRequest)
            .map(benefitService::create)
            .map(benefitMapper::toReponse)
            .get()
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findByCardNumber(@RequestParam cardNumber: String): BenefitResponse {
        return Optional.of(cardNumber)
            .map(benefitService::findByCardNumber)
            .map(benefitMapper::toReponse)
            .get()
    }


    @PutMapping("{id}/activate")
    @ResponseStatus(HttpStatus.OK)
    fun activateCard(@PathVariable id: Long) {
        Optional.of(id)
            .map(benefitService::activeBenefit)
            .get()
    }


    @PutMapping("{id}/inactivate")
    @ResponseStatus(HttpStatus.OK)
    fun inactivateCard(@PathVariable id: Long) {
        Optional.of(id)
            .map(benefitService::inactiveBenefit)
            .get()
    }
}