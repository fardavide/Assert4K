@file:Suppress("unused")

package assert4k

import kotlin.test.assertEquals

/**
 * Equality check between 2 [String], ignoring the case
 * `` assert that "hello" `equals no case` "HELlo"
 */
infix fun Asserter<String>.`equals no case`(expected: String) =
    equalsNoCase(expected)
// region overloads
infix fun Asserter<String>.`equals no case`(withMessage: WithMessage<String>) =
    equalsNoCase(withMessage)

infix fun Asserter<String>.equalsNoCase(expected: String) =
    assertEquals(expected.toUpperCase(), actual?.toUpperCase())

infix fun Asserter<String>.equalsNoCase(withMessage: WithMessage<String>) =
    assertEquals(withMessage.value.toUpperCase(), actual?.toUpperCase(), withMessage.message)
// endregion
