package assert4k

/**
 * @return [WithMessage] of [T] from the receiver and the [message] lambda
 * `` assert that "Hello" equals "Hello" { "I am the message" } ``
 */
@Deprecated(
    "'invoke' can cause conflicting declarations.\nWill be removed in 0.10",
    ReplaceWith("this / message", "assert4k.div")
)
operator fun <T> T.invoke(message: MessageBuilder<T>): WithMessage<T> =
    WithMessage(this, message(this))

/**
 * @return [WithMessage] of [T] from the receiver and the [message] lambda
 * `` assert that "Hello" equals "Hello" / { "I am the message" } ``
 */
operator fun <T> T.div(message: MessageBuilder<T>) =
    WithMessage(this, message(this))

/**
 * @return [WithMessage] of [T] from the receiver and the [message] lambda
 * `` assert that "Hello" equals "Hello" / "I am the message" ``
 */
operator fun <T> T.div(message: String) =
    WithMessage(this, message)

/**
 * Holds a value [T] and relative assertion's message
 */
class WithMessage<T> internal constructor(val value: T, val message: String)

typealias MessageBuilder<T> = (T) -> String
