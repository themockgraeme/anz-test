package com.kizio.anztest

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for the [MainActivity.Population] class that execute on the local host.
 */
class PopulationUnitTest {

    /**
     * The [MainActivity.Population] class to test.
     */
    private var population: MainActivity.Population? = null

    /**
     * Sets up the test data.
     */
    @Before
    fun setup() {
        val populationData = arrayOf(
            MainActivity.Lifespan(1900, 2000),
            MainActivity.Lifespan(1900, 1950),
            MainActivity.Lifespan(1950))
            MainActivity.Lifespan(1951, 1980)

        population = MainActivity.Population(populationData)
    }

    /**
     * Clears the test data.
     */
    @After
    fun teardown() {
        population = null
    }

    /**
     * Tests that no-one is alive before anyone is born.
     */
    @Test
    fun testPopulationIn1899() {
        assertEquals("Shouldn't be anyone alive in 1899",
            0, population?.getPopulationInYear(1899))
    }

    /**
     * Tests the population size in 1900.
     */
    @Test
    fun testPopulationIn1900() {
        assertEquals("Population not what was expected in 1900",
            2, population?.getPopulationInYear(1900))
    }

    /**
     * Tests the population size in 1950.
     */
    @Test
    fun testPopulationIn1950() {
        assertEquals("Population not what was expected in 1950",
            3, population?.getPopulationInYear(1950))
    }

    /**
     * Tests the population size in 1951.
     */
    @Test
    fun testPopulationIn1951() {
        assertEquals("Population not what was expected in 1951",
            2, population?.getPopulationInYear(1951))
    }

    /**
     * Tests the population size in 2000.
     */
    @Test
    fun testPopulationIn2000() {
        assertEquals("Population not what was expected in 2000",
            2, population?.getPopulationInYear(2000))
    }

    /**
     * Tests the population size in 2001.
     */
    @Test
    fun testPopulationIn2001() {
        assertEquals("Population not what was expected in 2001",
            1, population?.getPopulationInYear(2001))
    }

    /**
     * Tests whether the population is reported as falling in the correct years.
     */
    @Test
    fun testPopulationFallYears() {
        val fallYears = population?.getYearsPopulationDecreased()
        val expected = arrayOf(1951, 2001)

        assertNotNull("Expected a non-null result", fallYears)

        // This is a bit long-winded because array comparisons in assert have been deprecated!
        assertEquals("Different array sizes", 2, fallYears?.size)
        assertEquals("First value should be 1951", 1951, fallYears?.get(0))
        assertEquals("Second value should be 2001", 2001, fallYears?.get(1))
    }
}
