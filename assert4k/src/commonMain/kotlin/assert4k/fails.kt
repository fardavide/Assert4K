@file:Suppress("unused", "ClassName", "UNUSED_PARAMETER")

package assert4k

import assert4k.internal.coContinuation
import assert4k.internal.continuation
import kotlin.jvm.JvmName
import kotlin.test.assertFails
import kotlin.test.assertFailsWith

/**
 * Asserts that given function [block] fails by throwing an exception.
 * `` assert that fails { throw Exception() } ``
 *
 * @return An exception that was expected to be thrown and was successfully caught.
 * The returned exception can be inspected further, for example by asserting its property values.
 *
 * Note: suspend functions cannot be called in this block because [block] is not inlined, use [coFails] instead.
 */
fun fails(block: () -> Unit) =
    continuation { assertFails(block = block) }

suspend fun coFails(block: suspend () -> Unit) =
    coContinuation { assertFails { block() } }

/**
 * Asserts that a [block] fails with a specific exception of type [T] being thrown.
 * `` assert that fails<IllegalStateException> { throw IllegalStateException() } ``
 *
 * @return An exception of the expected exception type [T] that successfully caught.
 * The returned exception can be inspected further, for example by asserting its property values.
 *
 * Note: suspend functions cannot be called in this block because [block] is not inlined, use [coFails] instead.
 */
@JvmName("failsWithParam")
inline fun <reified T : Throwable> fails(crossinline block: () -> Unit) =
    continuation { assertFailsWith<T>(block = block) }

@JvmName("coFailsWithParam")
suspend inline fun <reified T : Throwable> coFails(crossinline block: suspend () -> Unit) =
    coContinuation { assertFailsWith<T> { block() } }

/**
 * Equality check on Exception's message.
 * `` assert that fails { throw Exception("my message") } with "my message" ``
 */
infix fun <T : Throwable> T.with(message: String) =
    assert that this.message equals message / "Exception's message does not match."
