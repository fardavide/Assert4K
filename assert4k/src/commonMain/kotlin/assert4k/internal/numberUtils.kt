package assert4k.internal

@PublishedApi
internal fun Float.fixToDouble() =
    toString().toDouble()

@PublishedApi
internal fun Float.toIntOrNull() =
    if (this % 1 == 0f) toInt() else null

@PublishedApi
internal fun Float.toLongOrNull() =
    if (this % 1 == 0f) toLong() else null

@PublishedApi
internal fun Float.toUIntOrNull() =
    if (this % 1 == 0f) toUInt() else null

@PublishedApi
internal fun Double.toIntOrNull() =
    if (this % 1 == 0.0) toInt() else null

@PublishedApi
internal fun Double.toLongOrNull() =
    if (this % 1 == 0.0) toLong() else null

@PublishedApi
internal fun Double.toUIntOrNull() =
    if (this % 1 == 0.0) toUInt() else null
