package assert4k.internal

// region cast
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
// endregion

// region compare
internal operator fun <A : Number, B : Number> A.compareTo(other: B): Int {
    return when (this) {
        is Int -> compareTo(other)
        is Long -> compareTo(other)
        is Double -> compareTo(other)
        is Float -> compareTo(other)
        else -> throw UnsupportedOperationException("Comparison from ${this::class.simpleName} is not supported yet")
    }
}

internal operator fun <B : Number> Int.compareTo(other: B): Int {
    return when (other) {
        is Int -> compareTo(other)
        is Long -> compareTo(other)
        is Double -> compareTo(other)
        is Float -> compareTo(other)
        else -> throw UnsupportedOperationException(
            "Comparison between ${this::class.simpleName} and ${other::class.simpleName} is not supported yet")
    }
}

internal operator fun <B : Number> Long.compareTo(other: B): Int {
    return when (other) {
        is Int -> compareTo(other)
        is Long -> compareTo(other)
        is Double -> compareTo(other)
        is Float -> compareTo(other)
        else -> throw UnsupportedOperationException(
            "Comparison between ${this::class.simpleName} and ${other::class.simpleName} is not supported yet")
    }
}

internal operator fun <B : Number> Double.compareTo(other: B): Int {
    return when (other) {
        is Int -> compareTo(other)
        is Long -> compareTo(other)
        is Double -> compareTo(other)
        is Float -> compareTo(other)
        else -> throw UnsupportedOperationException(
            "Comparison between ${this::class.simpleName} and ${other::class.simpleName} is not supported yet")
    }
}

internal operator fun <B : Number> Float.compareTo(other: B): Int {
    return when (other) {
        is Int -> compareTo(other)
        is Long -> compareTo(other)
        is Double -> compareTo(other)
        is Float -> compareTo(other)
        else -> throw UnsupportedOperationException(
            "Comparison between ${this::class.simpleName} and ${other::class.simpleName} is not supported yet")
    }
}
// end region
