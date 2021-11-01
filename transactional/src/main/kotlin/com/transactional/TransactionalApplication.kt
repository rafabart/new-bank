package com.transactional

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class TransactionalApplication

fun main(args: Array<String>) {
	runApplication<TransactionalApplication>(*args)
}
