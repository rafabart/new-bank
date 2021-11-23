package com.limitbalance

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LimitBalanceApplication

fun main(args: Array<String>) {
	runApplication<LimitBalanceApplication>(*args)
}
