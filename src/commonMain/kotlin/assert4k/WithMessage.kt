package assert4k

/**
 * @return [WithMessage] of [T] from the receiver and the [message] lambda
 * `` assert that "Hello" equals "Hello" { "I am the message" } ``
 */
operator fun <T> T.invoke(message: MessageBuilder) =
    WithMessage(this, message())

/**
 * Holds a value [T] and relative assertion's message
 */
class WithMessage<T> internal constructor(val value: T, val message: String)

typealias MessageBuilder = () -> String
