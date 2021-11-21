package com.transactional.client

import com.transactional.domain.response.CardClientResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    value = "\${client.card-api.value}",
    url = "\${client.card-api.url}"
)
interface CardClient {


    @RequestMapping(method = [RequestMethod.GET], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createCard(@RequestParam cardNumber: String): CardClientResponse
}