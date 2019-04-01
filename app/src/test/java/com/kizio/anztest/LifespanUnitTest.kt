package com.kizio.anztest

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for the [MainActivity.Lifespan] class that execute on the local host.
 */
class LifespanUnitTest {

    /**
     * Tests that an individual is alive in their birth year.
     */
    @Test
    fun isAliveInBirthYear() {
        val lifespan = MainActivity.Lifespan(2000)

        assertEquals("Should be alive in their birth year",
            true, lifespan.isAliveInYear(2000))
    }

    /**
     * Tests that an individual is not alive before their birth year.
     */
    @Test
    fun isNotAliveBeforeBirthYear() {
        val lifespan = MainActivity.Lifespan(2000)

        assertEquals("Shouldn't be alive before their birth year",
            false, lifespan.isAliveInYear(1999))
    }

    /**
     * Tests that an individual is alive if their birth and death years are the same.
     */
    @Test
    fun isAliveIfLivedAYear() {
        val lifespan = MainActivity.Lifespan(2000, 2000)

        assertEquals("Should be alive in their year of life",
            true, lifespan.isAliveInYear(2000))
    }

    /**
     * Tests that an individual is alive if no death date has been given.
     */
    @Test
    fun isAliveIfNullDeathDate() {
        val lifespan = MainActivity.Lifespan(2000)

        assertEquals("Should be alive if death date is null",
            true, lifespan.isAliveInYear(2020))
    }

    /**
     * Tests that an individual is alive before their death year.
     */
    @Test
    fun isAliveBeforeDeathDate() {
        val lifespan = MainActivity.Lifespan(1900, 2000)

        assertEquals("Should be alive before death date",
            true, lifespan.isAliveInYear(1999))
    }

    /**
     * Tests that an individual is dead after their death year.
     */
    @Test
    fun isDeadAfterDeathDate() {
        val lifespan = MainActivity.Lifespan(1900, 2000)

        assertEquals("Shouldn't be alive after death date",
            false, lifespan.isAliveInYear(2001))
    }
}
