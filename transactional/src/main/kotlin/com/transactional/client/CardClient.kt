package com.transactional.client

import com.transactional.domain.response.CardClientResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "cards", url = "http://localhost:8081/cards", fallback = CardClientFallback::class)
interface CardClient {


    @RequestMapping(method = [RequestMethod.GET], consumes = ["application/json"])
    fun findByCardNumber(@RequestParam cardNumber: String): CardClientResponse
}