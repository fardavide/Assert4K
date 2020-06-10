@file:Suppress("unused", "UNUSED_PARAMETER")

package assert4k

import kotlin.js.JsName
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * Entity used as parameter null check
 * `` assert that obj1 `is` Null
 */
object Null

/**
 * Null check of an object
 * `` assert that obj1 `is` Null
 */
infix fun <T : Any?> Asserter<T>.`is`(_null: Null) =
    assertNull(value)
// region overloads
// `is` w/ msg
infix fun <T : Any?> Asserter<T>.`is`(withMessage: WithMessage<Null>) =
    assertNull(value, withMessage.message)

// `is null`
@JsName("is_null")
fun <T : Any?> Asserter<T>.`is null`() =
    `is`(Null)

// `is null` w/ msg
@JsName("is_null_with_message")
infix fun <T : Any?> Asserter<T>.`is null`(message: MessageBuilder<Null>) =
    `is`(Null(message))

// isNull
fun <T : Any?> Asserter<T>.isNull() =
    `is null`()

// isNull w/ msg
infix fun <T : Any?> Asserter<T>.isNull(message: MessageBuilder<Null>) =
    `is null`(message)

// toBe
infix fun <T : Any?> Asserter<T>.toBe(_null: Null) =
    `is null`()

// toBe w/ msg
infix fun <T : Any?> Asserter<T>.toBe(withMessage: WithMessage<Null>) =
    `is`(withMessage)
// endregion

/**
 * Null check of an object
 * `` assert that obj1 `is` Null
 */
@JsName("is_not")
infix fun <T : Any> Asserter<T?>.`is not`(_null: Null) =
    assertNotNull(value)
// region overloads
// `is not` w/ msg
@JsName("is_not_with_message")
infix fun <T : Any> Asserter<T?>.`is not`(withMessage: WithMessage<Null>) =
    assertNotNull(value, withMessage.message)

// `is not null`
@JsName("is_not_null")
fun <T : Any> Asserter<T?>.`is not null`() =
    `is not`(Null)

// `is not null` w/ msg
@JsName("is_not_null_with_message")
infix fun <T : Any> Asserter<T?>.`is not null`(message: MessageBuilder<Null>) =
    `is not`(Null { message(Null) })

// isNotNull
fun <T : Any> Asserter<T?>.isNotNull() =
    `is not null`()

// isNotNull w/ msg
infix fun <T : Any> Asserter<T?>.isNotNull(message: MessageBuilder<Null>) =
    `is not null`(message)

// isNot
infix fun <T : Any> Asserter<T?>.isNot(_null: Null) =
    `is not null`()

// isNot w/ msg
infix fun <T : Any> Asserter<T?>.isNot(withMessage: WithMessage<Null>) =
    `is not`(withMessage)
// endregion
