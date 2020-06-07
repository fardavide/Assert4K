package assert4k.internal

import assert4k.AssertionContinuation

@PublishedApi
internal fun <T> continuation(
    block: () -> T
) = object : AssertionContinuation<T>() {
    override fun invoke() = block()
}

internal fun <T> continuationOf(
    assertionBlock: (() -> Unit) -> T,
    block: () -> Unit
) = object : AssertionContinuation<T>() {
    override fun invoke() = assertionBlock(block)
}
