package com.kizio.anztest

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Activity for the ANZ coding challenge.
 */
class MainActivity : AppCompatActivity() {
    /**
     * Represents the lifespan of an individual.
     *
     * @constructor Creates an instance of the [Lifespan] class.
     * @param born An [Int] representing the birth year of an individual
     * @param died An [Int] representing the year of death, null indicates still alive
     */
    class Lifespan (val born: Int, val died: Int?) {

        /**
         * Secondary constructor for an individual who's not yet dead.
         *
         * @param birthYear An [Int] representing the birth year of an individual
         */
        constructor(birthYear: Int) : this (birthYear, null)

        /**
         * Checks whether an individual was alive in a given year. The assumption, as per the
         * coding challenge instructions, is that if they were born in that year, or died in it,
         * they'll be alive.
         *
         * @param year An [Int] representing the year to be checked
         * @return True if the individual was alive in that year, false otherwise
         */
        fun isAliveInYear (year: Int) : Boolean {
            return (year >= born) && (died == null || died >= year)
        }
    }

    /**
     * Simple class allowing an [Array] of [Lifespan] objects to be queried.
     *
     * @constructor Creates an instance of the [Population] object.
     * @param lifespans The [Array] of [Lifespan] objects representing the population
     */
    class Population (private val lifespans: Array<Lifespan>) {

        /**
         * Gets the population size in the specified year.
         *
         * @param year The year to test
         * @return The size of the population in that year
         */
        fun getPopulationInYear (year: Int) : Int {
            var population = 0

            for (lifespan in lifespans) {
                if (lifespan.isAliveInYear(year)) {
                    population ++
                }
            }

            return population
        }

        /**
         * Get the years when the population decreased.
         * <p>
         * If I wanted to cache the results, I'd add a HashMap to hold the population in a given
         * year, so I don't need to recalculate it.
         * </p>
         *
         * @return An [Int] [Array] containing the years when the population decreased
         */
        fun getYearsPopulationDecreased() : Array<Int> {
            val populationFallYears = TreeSet<Int>()

            // Loop over the lifespans. The only years in which a population can fall is when
            // someone has died, so only check those.
            for (lifespan in lifespans) {
                lifespan.died?.let {year ->
                    val nextYear = year + 1

                    // We're interested in the year after someone dies, hence the app stores the
                    // nextYear variable, and checks for its presence.
                    if (!populationFallYears.contains(nextYear) &&
                        getPopulationInYear(year) > getPopulationInYear(nextYear)) {
                        populationFallYears.add(nextYear)
                    }
                }
            }

            return populationFallYears.toArray(arrayOfNulls(populationFallYears.size))
        }
    }

    /**
     * [Array] of [Lifespan] objects representing the population. These have been copied from the
     * coding challenge.
     */
    private val populationData = arrayOf(
        Lifespan(1902, 1991),
        Lifespan(1941, 1978),
        Lifespan(2004),
        Lifespan(1957),
        Lifespan(1989, 2008),
        Lifespan(1909, 2005),
        Lifespan(1918),
        Lifespan(1913, 2010),
        Lifespan(1979),
        Lifespan(1961, 2002),
        Lifespan(1977, 2003),
        Lifespan(1909, 1991)
    )

    /**
     * The [Population] object for the app to query.
     */
    private val population = Population(populationData)

    /**
     * Invoked when the [MainActivity] is created.
     *
     * @param savedInstanceState A [Bundle] containing data to reuse if possible
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val years = population.getYearsPopulationDecreased()
        val adapter = ArrayAdapter(this, R.layout.view_year, years)
        list_view.adapter = adapter
    }
}
