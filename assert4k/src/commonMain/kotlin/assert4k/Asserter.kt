@file:Suppress("ClassName", "unused")

package assert4k

/**
 * Entry point of every assertion.
 * It is supposed to be followed by [that] ( `` assert that <actual> ... `` )
 */
object assert

/**
 * @return [Asserter] for the type of [value]
 * `` assert that actualObject ... ``
 */
infix fun <T> assert.that(value: T?): Asserter<T> = object : Asserter<T> {
    override val value = value
}

/**
 * Runs a multi-assertion
 * @see plus
 */
infix fun <T> assert.that(continuation: AssertionContinuation<T>) =
    continuation()

/**
 * @return [AssertionBlock] for continue without an actual value
 * `` assert that fails { /* failing code here */ } ``
 */
infix fun <T> assert.that(multiAssertions: Pair<T, (Asserter<T>) -> Unit>) =
    multiAssertions.second(that(multiAssertions.first))

/**
 * Enable to run multiple assertions using the receiver [Asserter] as lambda parameter
 * ```
assert that "hello" +{ string ->
    string `not equals` "ciao"
    string contains "lo"
}
 * ```
 */
infix operator fun <T> T.plus(assertionsBlock: (Asserter<T>) -> Unit) =
    this to assertionsBlock


interface Asserter<out T> {
    val value: T?
}

abstract class AssertionContinuation<T> @PublishedApi internal constructor() {
    internal abstract operator fun invoke() : T
}
abstract class SuspendAssertionContinuation<T> @PublishedApi internal constructor() {
    internal abstract suspend operator fun invoke() : T
}

typealias Cont<T> = () -> T

object AssertionBlock
