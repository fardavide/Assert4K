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

/**
 * Enable to run multiple assertions using the receiver [Asserter] as lambda parameter
 * ```
assert that User(name = "Davide", age = 29) *{ // this = MyClass
    +name equals "Davide"
    +age equals 29
}
 * ```
 * @see unaryPlus
 */
infix operator fun <T> T.times(assertionsBlock: T.(Asserter<T>) -> Unit) =
    this to assertionsBlock

/**
 * Enable to create an [Asserter] out of the receiver [T]
 * ```
assert that User(name = "Davide", age = 29) *{ // this = MyClass
    +name equals "Davide"
    +age equals 29
}
 * ```
 * @see times
 */
operator fun <T> T.unaryPlus() =
    if (this is UnaryFix<*>) assert that underlying
    else assert that this

/**
 * Applies a fix for types that has [unaryPlus] as language feature, like [Int]
 * `` +intValue() ``
 * @see unaryPlus
 */
operator fun <T> T.invoke() = UnaryFix(this)

class UnaryFix<T>(val underlying: T)

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
