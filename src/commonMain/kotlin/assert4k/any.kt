package assert4k

import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Assert that [actual] returns a `true` value
 * `` assert that myClass.isReady() ``
 */
infix fun assert.that(actual: Boolean) = assertTrue(actual)
// region overloads
infix fun assert.that(withMessage: WithMessage<Boolean>) = assertTrue(withMessage.value, withMessage.message)
// endregion

/**
 * Assert that lambda [block] returns a `true` value
 * `` assert that { obj1 != obj2 } ``
 */
infix fun assert.that(block: () -> Boolean) = assertTrue(block())

/**
 * Equality check between 2 objects
 * `` assert that obj1 equals obj2 ``
 */
infix fun <T> Asserter<T>.equals(expected: T) =
    assertEquals(expected, actual)
// region overloads
infix fun <T> Asserter<T>.equals(withMessage: WithMessage<T>) =
    assertEquals(withMessage.value, actual, withMessage.message)
// endregion
