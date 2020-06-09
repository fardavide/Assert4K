package assert4k.internal

import assert4k.AssertionContinuation
import assert4k.SuspendAssertionContinuation

@PublishedApi
internal fun <T> continuation(
    block: () -> T
) = object : AssertionContinuation<T>() {
    override fun invoke() = block()
}
@PublishedApi
internal suspend fun <T> coContinuation(
    block: suspend () -> T
) = object : SuspendAssertionContinuation<T>() {
    override suspend fun invoke() = block()
}
