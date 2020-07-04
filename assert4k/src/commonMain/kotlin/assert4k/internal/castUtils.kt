package assert4k.internal

import kotlin.reflect.KClass

@PublishedApi
internal inline fun <reified T> Any.smartCast(value: T): T? {
    value ?: return null
    @Suppress("UNNECESSARY_NOT_NULL_ASSERTION") // AS bug :/
    val kClass = value!!::class
    return when (this) {

        is Double -> cast(kClass)
        is Float -> cast(kClass)
        is Int -> cast(kClass)
        is Long -> cast(kClass)
        is String -> cast(kClass)
        is UInt -> cast(kClass)

        is IntRange -> cast(value)

        else -> null
    }
}

// region numbers
@PublishedApi
internal inline fun <reified T> Double.cast(kClass: KClass<*>): T? {
    return when (kClass) {
        Double::class -> this
        Float::class -> toFloat()
        Int::class -> toIntOrNull()
        Long::class -> toLongOrNull()
        String::class -> toString()
        UInt::class -> toUIntOrNull()
        else -> null
    } as T?
}

@PublishedApi
internal inline fun <reified T> Float.cast(kClass: KClass<*>): T? {
    return when (kClass) {
        Double::class -> fixToDouble()
        Float::class -> this
        Int::class -> toIntOrNull()
        Long::class -> toLongOrNull()
        String::class -> toString()
        UInt::class -> toUIntOrNull()
        else -> null
    } as T?
}

@PublishedApi
internal inline fun <reified T> Int.cast(kClass: KClass<*>): T? {
    return when (kClass) {
        Double::class -> toDouble()
        Float::class -> toFloat()
        Int::class -> this
        Long::class -> toLong()
        String::class -> toString()
        UInt::class -> toUInt()
        else -> null
    } as T?
}

@PublishedApi
internal inline fun <reified T> Long.cast(kClass: KClass<*>): T? {
    return when (kClass) {
        Double::class -> toDouble()
        Float::class -> toFloat()
        Int::class -> toInt()
        Long::class -> this
        String::class -> toString()
        UInt::class -> toUInt()
        else -> null
    } as T?
}

@PublishedApi
internal inline fun <reified T> String.cast(kClass: KClass<*>): T? {
    return when (kClass) {
        Double::class -> toDouble()
        Float::class -> toFloat()
        Int::class -> toInt()
        Long::class -> toLong()
        String::class -> this
        UInt::class -> toUInt()
        else -> null
    } as T?
}

@PublishedApi
internal inline fun <reified T> UInt.cast(kClass: KClass<*>): T? {
    return when (kClass) {
        Double::class -> toDouble()
        Float::class -> toFloat()
        Int::class -> toInt()
        Long::class -> toLong()
        String::class -> toString()
        UInt::class -> this
        else -> null
    } as T?
}
// endregion

// region ranges
@PublishedApi
internal inline fun <reified T> IntRange.cast(range: T): T? {
    return when (range) {
        is ClosedFloatingPointRange<*> -> {
            when (range.start) {
                is Double -> first.toDouble()..last.toDouble()
                is Float -> first.toFloat()..last.toFloat()
                else -> null
            }
        }
        is IntRange -> this
        is LongRange -> first.toLong()..last.toLong()
        else -> null
    } as T?
}
// endregion
