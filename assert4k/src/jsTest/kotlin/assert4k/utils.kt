package assert4k

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

actual fun blocking(block: suspend () -> Unit): dynamic =
    GlobalScope.promise { block() }
