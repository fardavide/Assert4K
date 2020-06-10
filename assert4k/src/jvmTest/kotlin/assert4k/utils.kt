package assert4k

import kotlinx.coroutines.runBlocking

actual fun blocking(block: suspend () -> Unit) =
    runBlocking { block() }
