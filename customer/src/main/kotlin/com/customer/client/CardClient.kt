package com.customer.client

import com.customer.domain.request.CardClientRequest
import com.customer.domain.response.CardClientResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(
    value = "\${client.card-api.value}",
    url = "\${client.card-api.url}"
)
interface CardClient {

    @RequestMapping(method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createCard(@RequestBody cardClientRequest: CardClientRequest): ResponseEntity<CardClientResponse>
}