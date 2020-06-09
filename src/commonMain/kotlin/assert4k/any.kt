@file:Suppress("unused")

package assert4k

import kotlin.js.JsName
import kotlin.jvm.JvmName
import kotlin.test.*

/**
 * Assert that [actual] returns a `true` value
 * `` assert that myClass.isReady() ``
 */
infix fun assert.that(actual: Boolean) =
    assertTrue(actual,"Expected actual to return a true value")
// region overloads
@JvmName("thatBooleanWithMessage")
infix fun assert.that(withMessage: WithMessage<Boolean>) =
    assertTrue(withMessage.value, withMessage.message)

/**
 * Assert that lambda [block] returns a `true` value
 * `` assert that { obj1 != obj2 } ``
 */
infix fun assert.that(block: () -> Boolean) =
    that(block { "Expected equation to return a true value" } )

@JvmName("thatBooleanLambdaWithMessage")
infix fun assert.that(withMessage: WithMessage<() -> Boolean>) =
    assertTrue(withMessage.value(), withMessage.message)
// endregion

/**
 * Equality check between 2 objects
 * `` assert that obj1 equals obj2 ``
 */
infix fun <T> Asserter<T>.equals(expected: T) =
    assertEquals(expected, value)
// region overloads
infix fun <T> Asserter<T>.equals(withMessage: WithMessage<T>) =
    assertEquals(withMessage.value, value, withMessage.message)
// endregion

/**
 * Equality check between 2 objects
 * `` assert that obj1 `not equals` obj2 ``
 */
@JsName("not_equals")
infix fun <T> Asserter<T>.`not equals`(expected: T) =
    assertNotEquals(expected, value)
// region overloads
@JsName("not_equals_with_message")
infix fun <T> Asserter<T>.`not equals`(withMessage: WithMessage<T>) =
    assertNotEquals(withMessage.value, value, withMessage.message)

infix fun <T> Asserter<T>.notEquals(expected: T) =
    `not equals`(expected)

infix fun <T> Asserter<T>.notEquals(withMessage: WithMessage<T>) =
    `not equals`(withMessage)
// endregion

/**
 * Instance check between 2 objects
 * `` assert obj1 same obj2
 */
infix fun <T> Asserter<T>.same(expected: T) =
    assertSame(expected, value)
// region overloads
infix fun <T> Asserter<T>.same(withMessage: WithMessage<T>) =
    assertSame(withMessage.value, value, withMessage.message)
// endregion

/**
 * Instance check between 2 objects
 * `` assert obj1 same obj2
 */
@JsName("not_same")
infix fun <T> Asserter<T>.`not same`(expected: T) =
    assertNotSame(expected, value)
// region overloads
@JsName("not_same_with_message")
infix fun <T> Asserter<T>.`not same`(withMessage: WithMessage<T>) =
    assertNotSame(withMessage.value, value, withMessage.message)

infix fun <T> Asserter<T>.notSame(expected: T) =
    `not same`(expected)

infix fun <T> Asserter<T>.notSame(withMessage: WithMessage<T>) =
    `not same`(withMessage)
// endregion
