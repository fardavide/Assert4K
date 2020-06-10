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
 * @return [AssertionBlock] for continue without an actual value
 * `` assert that fails { /* failing code here */ } ``
 */
infix fun <T> assert.that(continuation: AssertionContinuation<T>) =
    continuation()

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
