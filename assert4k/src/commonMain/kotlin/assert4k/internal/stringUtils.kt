package assert4k.internal

import kotlin.text.contains as kContains

internal infix fun String?.contains(other: String) =
    this?.kContains(other, ignoreCase = false) ?: false

internal infix fun String?.contains(regex: Regex) =
    this?.kContains(regex) ?: false

internal infix fun String?.containsNoCase(other: String) =
    this?.kContains(other, ignoreCase = true) ?: false

internal infix fun String?.equals(regex: Regex) =
    this?.matches(regex) ?: false
