package com.example.jetpackcomposebasics

import org.junit.Assert.*
import org.junit.Test
import java.text.NumberFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `test number formatter to US`() {
        val number = 1000000000.0
        val country = "US"
        val language = "en"
        val str: String = NumberFormat.getCurrencyInstance(Locale(language, country)).format(number)

        assertEquals("\$1,000,000,000.00", str)
    }

    @Test
    fun `test number formatter to Pounds`() {
        val number = 1000000000.0
        val country = "UK"
        val language = "en"
        val str: String = NumberFormat.getCurrencyInstance(Locale(language, country)).format(number)

        assertEquals("¤1,000,000,000.00", str)
    }

    @Test
    fun `test number formatter to Euros`() {
        val number = 1000000000.0
        val country = "FR"
        val language = "fr"
        val str: String = NumberFormat.getCurrencyInstance(Locale(language, country)).format(number)

        assertEquals("1 000 000 000,00 €", str)
    }
}