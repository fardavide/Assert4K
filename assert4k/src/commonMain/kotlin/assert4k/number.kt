@file:Suppress("unused")

package assert4k

import assert4k.internal.*
import kotlin.js.JsName
import kotlin.jvm.JvmName

/**
 * Verify that actual Number is in [range]
 * `` assert that 12 between 10..20 ``
 */
infix fun Asserter<Int>.between(range: IntRange) =
    assert that value between range {
        "Expected value <$value> to be between <${range.first}> and <${range.last}>"
    }
// region overloads
infix fun Asserter<Int?>.between(withMessage: WithMessage<IntRange>) =
    assert that (value in withMessage.value) { withMessage.message }

// Long
@JvmName("Long_between")
infix fun Asserter<Long>.between(range: LongRange) =
    assert that (value in range) {
        "Expected value <$value> to be between <${range.first}> and <${range.last}>"
    }
@JvmName("Long_between_with_message")
infix fun Asserter<Long>.between(withMessage: WithMessage<LongRange>) =
    assert that (value in withMessage.value) { withMessage.message }

@JvmName("Long_between_Int")
infix fun Asserter<Long>.between(range: IntRange) =
    assert that (value in range.smartCast(0L..0L)!!) {
        "Expected value <$value> to be between <${range.first}> and <${range.last}>"
    }
@JvmName("Long_between_Int_with_message")
infix fun Asserter<Long>.between(withMessage: WithMessage<IntRange>) =
    assert that (value in withMessage.value.smartCast(0L..0L)!!) { withMessage.message }

// Double
@JvmName("Double_between")
infix fun Asserter<Double>.between(range: ClosedFloatingPointRange<Double>) =
    assert that (value?.let { it in range } ?: false) {
        "Expected value <$value> to be between <${range.start}> and <${range.endInclusive}>"
    }
@JvmName("Double_between_with_message")
infix fun Asserter<Double>.between(withMessage: WithMessage<ClosedFloatingPointRange<Double>>) =
    assert that (value?.let { it in withMessage.value } ?: false) { withMessage.message }

@JvmName("Double_between_Int")
infix fun Asserter<Double>.between(range: IntRange) =
    assert that (value?.let { it in range.smartCast(it..it)!! } ?: false) {
        "Expected value <$value> to be between <${range.first}> and <${range.last}>"
    }
@JvmName("Double_between_Int_with_message")
infix fun Asserter<Double>.between(withMessage: WithMessage<IntRange>) =
    assert that (value?.let { it in withMessage.value.smartCast(it..it)!! } ?: false) { withMessage.message }

// Float
@JvmName("Float_between")
infix fun Asserter<Float>.between(range: ClosedFloatingPointRange<Float>) =
    assert that (value?.let { it in range } ?: false) {
        "Expected value <$value> to be between <${range.start}> and <${range.endInclusive}>"
    }
@JvmName("Float_between_with_message")
infix fun Asserter<Float>.between(withMessage: WithMessage<ClosedFloatingPointRange<Float>>) =
    assert that (value?.let {it in withMessage.value } ?: false) { withMessage.message }

@JvmName("Float_between_Int")
infix fun Asserter<Float>.between(range: IntRange) =
    assert that (value?.let {it in range.smartCast(it..it)!! } ?: false) {
        "Expected value <$value> to be between <${range.first}> and <${range.last}>"
    }
@JvmName("Float_between_Int_with_message")
infix fun Asserter<Float>.between(withMessage: WithMessage<IntRange>) =
    assert that (value?.let { it in withMessage.value.smartCast(it..it)!! } ?: false) { withMessage.message }
// endregion

/**
 * Verify that actual [Number] is greater than [expected]
 * `` assert that 10 `greater than` 5 ``
 */
@JsName("greater_than")
infix fun Asserter<Number>.`greater than`(expected: Number) =
    assert that value `greater than` expected {
        "Expected value <$value> to be greater than <$expected>"
    }
// region overloads
@JsName("greater_than$1")
infix fun Asserter<Number?>.`greater than`(withMessage: WithMessage<Number>) =
    assert that (value != null && value!! > withMessage.value) {
        withMessage.message
    }

/**
 * Verify that actual [Number] is greater or equal [expected]
 * `` assert that 10 `greater than` 5 ``
 */
@JsName("greater_or_equal")
infix fun Asserter<Number>.`greater or equal`(expected: Number) =
    assert that value `greater or equal` expected {
        "Expected value <$value> to be greater or equal <$expected>"
    }
// region overloads
@JsName("greater_or_equal$1")
infix fun Asserter<Number?>.`greater or equal`(withMessage: WithMessage<Number>) =
    assert that (value != null && value!! >= withMessage.value) {
        withMessage.message
    }

/**
 * Verify that actual [Number] is less than [expected]
 * `` assert that 10 `less than` 5 ``
 */
@JsName("less_than")
infix fun Asserter<Number>.`less than`(expected: Number) =
    assert that value `less than` expected {
        "Expected value <$value> to be less than <$expected>"
    }
// region overloads
@JsName("less_than$1")
infix fun Asserter<Number?>.`less than`(withMessage: WithMessage<Number>) =
    assert that (value != null && value!! < withMessage.value) {
        withMessage.message
    }
// endregion

/**
 * Verify that actual [Number] is less or equal [expected]
 * `` assert that 10 `greater than` 5 ``
 */
@JsName("less_or_equal")
infix fun Asserter<Number>.`less or equal`(expected: Number) =
    assert that value `less or equal` expected {
        "Expected value <$value> to be less or equal to <$expected>"
    }
// region overloads
@JsName("less_or_equal$1")
infix fun Asserter<Number?>.`less or equal`(withMessage: WithMessage<Number>) =
    assert that (value != null && value!! <= withMessage.value) {
        withMessage.message
    }
// endregion
