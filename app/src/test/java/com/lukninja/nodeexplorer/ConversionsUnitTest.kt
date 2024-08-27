package com.lukninja.nodeexplorer

import com.lukninja.nodeexplorer.extensions.toBitcoin
import com.lukninja.nodeexplorer.extensions.toDate
import org.junit.Test

import org.junit.Assert.*
import java.math.BigDecimal

class ConversionsUnitTest {
    @Test
    fun testToDate() {
        val timestamp: Long = 1724457600

        val expectedDate = "23/08/2024"

        val actualDate = timestamp.toDate("dd/MM/yyyy")

        assertEquals(expectedDate, actualDate)
    }

    @Test
    fun testToBitcoin() {
        val satoshis = 15464503162L

        val expectedBitcoin = BigDecimal(satoshis).divide(BigDecimal(100_000_000)).toString()

        val actualBitcoin = satoshis.toBitcoin()

        assertEquals(expectedBitcoin, actualBitcoin)
    }
}