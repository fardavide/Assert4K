@file:Suppress("unused")

package assert4k

import assert4k.internal.smartCast
import kotlin.jvm.JvmName

/**
 * Verify that actual Number is in [range]
 * `` assert that 12 between 10..20 ``
 */
infix fun Asserter<Int>.between(range: IntRange) =
    assert that (value in range) {
        "Expected value <$value> to be between <${range.first}> and <${range.last}>"
    }

// region overloads
infix fun Asserter<Int>.between(withMessage: WithMessage<IntRange>) =
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
