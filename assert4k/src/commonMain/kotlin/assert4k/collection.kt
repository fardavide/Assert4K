@file:Suppress("unused", "ClassName", "UNUSED_PARAMETER")

package assert4k

import kotlin.js.JsName
import kotlin.jvm.JvmName

/**
 * Verify that actual [Collection] contains exactly same items as [expected] ignoring their order
 * `` assert that listOf(1, 2, 3) `equals no order` listOf(3, 2, 1) ``
 */
@JsName("equals_no_order")
infix fun <T, C : Collection<T>> Asserter<C>.`equals no order`(expected: C) {
    assert that value `equals no order` expected / {
        buildString {
            append("Expected Collection to contains <${expected.joinToString()}>, ")
            if (value == null) append("but is null")
            else append("but contains <${value!!.joinToString()}>")
        }
    }
}
// region overloads
@JsName("equals_no_order$1")
infix fun <T, C : Collection<T>> Asserter<C?>.`equals no order`(withMessage: WithMessage<C>) {
    assert that value `is not` Null
    val value = value!!
    assert that (value.size == withMessage.value.size) / withMessage.message
    val ml = value.toMutableList()
    for (e in withMessage.value) {
        assert that (ml.remove(e)) / withMessage.message
    }
    assert that ml `is` empty / withMessage.message
}
// endregion

/**
 * Verify that actual [Collection] contains [item]
 * `` assert that listOf(1, 2, 3) contains 3 ``
 */
infix fun <T, C : Collection<T>> Asserter<C?>.contains(item: T) =
    assert that value contains item / {
        buildString {
            append("Expected Collection to contains <$item>, ")
            if (value == null) append("but is null")
            else append("but contains <${value!!.joinToString()}>")
        }
    }
// region overloads
infix fun <T, C : Collection<T>> Asserter<C?>.contains(withMessage: WithMessage<T>) =
    assert that (value?.contains(withMessage.value) ?: false) / withMessage.message
// endregion

/**
 * Verify that actual [Collection] contains all the items in [items]
 * `` assert that listOf(1, 2, 3) `contains all` listOf(3, 2) ``
 */
@JsName("contains_all")
infix fun <T, C : Collection<T>> Asserter<C>.`contains all`(items: C) {
    assert that value `contains all` items / {
        buildString {
            append("Expected Collection to contains <${items.joinToString()}>, ")
            if (value == null) append("but is null")
            else append("but contains <${value!!.joinToString()}>")
        }
    }
}
// region overloads
@JsName("contains_all$1")
infix fun <T, C : Collection<T>> Asserter<C?>.`contains all`(withMessage: WithMessage<C>) {
    assert that (value?.containsAll(withMessage.value) ?: false) / withMessage.message
}
// endregion

object empty

/**
 * Verify that actual [Collection] is empty
 * `` assert that emptyList<Int>() `is` empty ``
 */
@JsName("is")
infix fun <T, C : Collection<T>> Asserter<C?>.`is`(_empty: empty) =
    assert that value `is` empty / {
        buildString {
            append("Expected Collection to be empty, ")
            if (value == null) append("but is null")
            else append("but has ${value?.size} elements")
        }
    }

// region overloads
@JvmName("is_empty_with_message")
@JsName("is$1")
infix fun <T, C : Collection<T>> Asserter<C?>.`is`(withMessage: WithMessage<empty>) =
    assert that (value?.isEmpty() ?: false) / withMessage.message
// endregion

@JsName("null_or_empty")
infix fun Null.or(_empty: empty) = `null or empty`

@JsName("null_or_empty$1")
object `null or empty`

/**
 * Verify that actual [Collection] is null or empty
 * `` assert that emptyList<Int>() `is` empty ``
 */
@JsName("is$2")
infix fun <T, C : Collection<T>> Asserter<C?>.`is`(nullOrEmpty: `null or empty`) =
    assert that value `is` `null or empty` / {
        "Expected Collection to be empty, but has ${value?.size} elements"
    }

// region overloads
@JvmName("is_null_or_empty_with_message")
@JsName("is$3")
infix fun <T, C : Collection<T>> Asserter<C?>.`is`(withMessage: WithMessage<`null or empty`>) =
    assert that (value.isNullOrEmpty()) / { withMessage.message }
// endregion

/**
 * Verify that actual [Collection] is not empty
 * `` assert that emptyList<Int>() `is` empty ``
 */
@JsName("is_not")
infix fun <T, C : Collection<T>> Asserter<C?>.`is not`(_empty: empty) =
    assert that value `is not` empty / {
        buildString {
            append("Expected Collection to not be empty, ")
            if (value == null) append("but is null")
            else append("but is empty")
        }
    }

// region overloads
@JvmName("is__not_empty_with_message")
@JsName("is_not$1")
infix fun <T, C : Collection<T>> Asserter<C?>.`is not`(withMessage: WithMessage<empty>) =
    assert that (value?.isNotEmpty() ?: false) / withMessage.message
// endregion
