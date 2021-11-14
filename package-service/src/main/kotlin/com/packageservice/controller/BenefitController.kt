package com.packageservice.controller

import com.packageservice.domain.request.BenefitRequest
import com.packageservice.domain.response.BenefitResponse
import com.packageservice.mapper.BenefitMapper
import com.packageservice.service.BenefitService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("benefits")
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


    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: Long): BenefitResponse {
        return Optional.of(id)
            .map(benefitService::findById)
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


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(
        @PathVariable id: Long,
        @RequestBody benefitRequest: BenefitRequest
    ): BenefitResponse {
        return Optional.of(id)
            .map { this.benefitService.update(id, benefitRequest) }
            .map(benefitMapper::toReponse)
            .get()
    }
}