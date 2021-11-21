package com.customer.controller

import com.customer.domain.request.CustomerRequest
import com.customer.domain.response.CustomerResponse
import com.customer.mapper.CustomerMapper
import com.customer.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("customers")
class CustomerController(

    private val customerMapper: CustomerMapper,
    private val customerService: CustomerService

) {

    //TODO: handlerException para o FeignClient
    @GetMapping("by-email")
    @ResponseStatus(HttpStatus.OK)
    fun findAllByEmail(
        @RequestParam(required = false) email: String?,
        @PageableDefault(
            size = 10,
            sort = ["name"],
            direction = Sort.Direction.DESC
        ) pagesConfig: Pageable
    ): Page<CustomerResponse> {

        return this.customerService.findAllByEmail(email, pagesConfig)
            .map(customerMapper::toResponse)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAll(
        @PageableDefault(
            size = 10,
            sort = ["name"],
            direction = Sort.Direction.DESC
        ) pagesConfig: Pageable
    ): Page<CustomerResponse> {

        return this.customerService.findAll(pagesConfig)
            .map(customerMapper::toResponse)
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customerRequest: CustomerRequest): CustomerResponse {
        return Optional.of(customerRequest)
            .map(customerService::create)
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