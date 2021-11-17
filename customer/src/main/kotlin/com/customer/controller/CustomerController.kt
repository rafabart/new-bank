package com.customer.controller

import com.customer.CustomerService
import com.customer.domain.request.CustomerRequest
import com.customer.domain.response.CustomerResponse
import com.customer.mapper.CustomerMapper
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("customers")
class CustomerController(

    private val customerMapper: CustomerMapper,
    private val customerService: CustomerService

) {

    //TODO: Flyway
    //TODO: Integração com card-api


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customerRequest: CustomerRequest): CustomerResponse {
        return Optional.of(customerRequest)
            .map(customerService::create)
            .map(customerMapper::toResponse)
            .get()
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findByEmail(@RequestParam email: String): CustomerResponse {
        return Optional.of(email)
            .map(customerService::findByEmail)
            .map(customerMapper::toResponse)
            .get()
    }


    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: UUID): CustomerResponse {
        return Optional.of(id)
            .map(customerService::findById)
            .map(customerMapper::toResponse)
            .get()
    }
}