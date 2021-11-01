package com.card.util

import org.apache.commons.lang3.RandomStringUtils

class CardNumberGenerator {

    companion object Factory {

        private const val separator: String = "-"
        private const val size: Int = 4

        fun generate(): String {
            return random()
                .plus(separator)
                .plus(random())
                .plus(separator)
                .plus(random())
                .plus(separator)
                .plus(random())
        }


        private fun random(): String {
            return RandomStringUtils.randomNumeric(size)
        }
    }
}
