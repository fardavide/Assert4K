@file:Suppress("ClassName")

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
infix fun <T> assert.that(value: T): Asserter<T> {
    @Suppress("UNCHECKED_CAST") // safe cast
    (value as? Pair<Any, Function2<Any, Asserter<Any>, Unit>>)?.let { (first, second) ->
        val asserter = assert that first
        second.invoke(first, asserter as Asserter<Any>)
        asserter
    }

    return object : Asserter<T> {
        override val value = value
    }
}

/**
 * Runs a multi-assertion
 */
infix fun <T> assert.that(continuation: AssertionContinuation<T>) =
    continuation()

/**
 * Enable to run multiple assertions using the receiver [Asserter] as lambda parameter
 * ```
assert that User(name = "Davide", age = 29) *{ // this = MyClass
    +name equals "Davide"
    +age equals 29
}
 * ```
 * or
 * ```
assert that "Hello" *{ string ->
    string `equals no case` "hello"
    string contains "lo"
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
    assert that this

operator fun <T> Fix<T>.unaryPlus() =
    assert that underlying

/**
 * Applies a fix for types that has [unaryPlus] as language feature, like [Int]
 * `` +intValue() ``
 * @see unaryPlus
 */
operator fun <T> T.invoke() = Fix(this)

class Fix<T>(val underlying: T)

interface Asserter<out T> {
    val value: T?
}

abstract class AssertionContinuation<T> @PublishedApi internal constructor() {
    internal abstract operator fun invoke() : T
}
abstract class SuspendAssertionContinuation<T> @PublishedApi internal constructor() {
    internal abstract suspend operator fun invoke() : T
}
