@file:Suppress("unused", "ClassName", "UNUSED_PARAMETER")

package assert4k

import assert4k.internal.contains
import assert4k.internal.containsNoCase
import assert4k.internal.equals
import kotlin.jvm.JvmName
import kotlin.test.assertEquals

/**
 * @return [Regex] with [RegexOption.IGNORE_CASE]
 * @param _case is object [case] ( required for infix function )
 */
infix fun String.no(_case: case): Regex =
    toRegex(RegexOption.IGNORE_CASE)

object case

/**
 * Equality check between a [String] and a [Regex]
 * `` assert that "hello" `equals no case` regex ``
 */
infix fun Asserter<String>.equals(regex: Regex) =
    assert that (value equals regex) { "Expected <$value> to contains <$regex>" }
// region overloads
@JvmName("equalsRegexWithMessage")
infix fun Asserter<String>.equals(withMessage: WithMessage<Regex>) =
    assert that (value equals withMessage.value) { withMessage.message }
// endregion

/**
 * Equality check between 2 [String], ignoring the case
 * `` assert that "hello" `equals no case` "HELlo" ``
 */
infix fun Asserter<String>.`equals no case`(expected: String) =
    assertEquals(expected.toUpperCase(), value?.toUpperCase())
// region overloads
infix fun Asserter<String>.`equals no case`(withMessage: WithMessage<String>) =
    assertEquals(withMessage.value.toUpperCase(), value?.toUpperCase(), withMessage.message)

infix fun Asserter<String>.equalsNoCase(expected: String) =
    `equals no case`(expected)

infix fun Asserter<String>.equalsNoCase(withMessage: WithMessage<String>) =
    `equals no case`(withMessage)
// endregion

/**
 * Verify that actual [String] contains [other]
 * `` assert that "Hello" contains "He" ``
 */
infix fun Asserter<String>.contains(other: String) =
    assert that (value contains other) {
        buildString {
            append("Expected <$value> to contains <$other>")
            if (value containsNoCase other) append(". It would match ignoring the case")
        }
    }
// region overloads
@JvmName("containsStringWithMessage")
infix fun Asserter<String>.contains(withMessage: WithMessage<String>) =
    assert that (value contains withMessage.value) { withMessage.message }
// endregion

/**
 * Verify that actual [String] contains [regex]
 * `` assert that "Hello" contains regex ``
 */
infix fun Asserter<String>.contains(regex: Regex) =
    assert that (value contains regex) { "Expected <$value> to contains <$regex>" }
// region overloads
@JvmName("containsRegexWithMessage")
infix fun Asserter<String>.contains(withMessage: WithMessage<Regex>) =
    assert that (value contains withMessage.value) { withMessage.message }
// endregion

/**
 * Verify that actual [String] contains [other]
 * `` assert that "Hello" contains "He" ``
 */
infix fun Asserter<String>.`contains no case`(other: String) =
    assert that (value containsNoCase other) { "Expected <$value> to contains <$other>" }
// region overloads
infix fun Asserter<String>.`contains no case`(withMessage: WithMessage<String>) =
    assert that (value containsNoCase withMessage.value) { withMessage.message }

infix fun Asserter<String>.containsNoCase(other: String) =
    `contains no case`(other)

infix fun Asserter<String>.containsNoCase(withMessage: WithMessage<String>) =
    `contains no case`(withMessage)
// endregion
