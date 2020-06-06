@file:Suppress("ClassName")

package assert4k

import kotlin.test.assertTrue

/**
 * Entry point of every assertion.
 * It is supposed to be followed by [that] ( `` assert that <actual> ... `` )
 */
object assert

/**
 * @return [Asserter] for the type of [actual]
 */
infix fun <T> assert.that(actual: T?): Asserter<T> = object : Asserter<T> {
    override val actual = actual
}

interface Asserter<T> {
    val actual: T?
}
