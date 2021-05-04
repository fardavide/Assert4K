@file:Suppress("PropertyName")

/*
 * Inject @JsName annotation for function names that are not supported on Js
 * Author: Davide Farella
 */

val DEBUG = false
fun logDebug(string: String) {
    if (DEBUG) println(string)
}

val sep: String = File.separator
val assert4k = "${rootDir.path}${sep}assert4k${sep}src${sep}common%%%${sep}kotlin${sep}assert4k"
val SRC_DIR = File(assert4k.replace("%%%", "Main")).also { check(it.exists()) }
val TEST_DIR = File(assert4k.replace("%%%", "Test")).also { check(it.exists()) }

/**
 * Take all nested `*.kt` files
 */
fun File.allSourceFiles(): List<File> {
    return listFiles().orEmpty().flatMap {
        when {
            it.isDirectory -> it.allSourceFiles()
            it.extension == "kt" -> listOf(it)
            else -> listOf()
        }
    }
}

val files = SRC_DIR.allSourceFiles() + TEST_DIR.allSourceFiles()
println("Injecting @JsName in ${files.size} files")
println(files.sortedBy { it.name }.joinToString { it.nameWithoutExtension })

// Keep track of name of annotated function with a count for each name, for avoid to have duplicated names,
// since Js doesn't support function with different parameters but same name
val fixedMap = mutableMapOf<String, Int>()

for (file in files) {
    logDebug("\nFile: ${file.nameWithoutExtension}")
    val tmp = File.createTempFile("tmp_", null, file.parentFile)

    tmp.bufferedWriter().use { writer ->

        var lastLine: String? = null
        file.forEachLine { line ->
            val lastLineTrim = lastLine?.trim()

            // Skip @JsName since we are goin' to inject it
            if (lastLineTrim?.startsWith("@JsName") == false)
                writer.appendln(lastLine)

            line.trim()
                // Skip comments
                .takeIfNotComment()
                ?.memberNameOrNull()
                ?.let {
                    // Reuse @JvmName if any
                    val origName = lastLineTrim?.fromJvmNameOrNull() ?: it
                    // Fix the name
                    val fixedName = origName
                        .replace(" ", "_")
                        .replace("'", "_")
                        .replace("-", "_")
                    // Add `$#` if name already present in file
                    val finalName = fixedName.withIdentifier(fixedMap)
                    val indentation = line.takeWhile { c -> c == ' ' }
                    writer.appendln("$indentation@JsName(\"$finalName\")")
                }

            lastLine = line
        }

        writer.appendln(lastLine)
    }

    tmp.renameTo(file)
}

fun String.takeIfNotComment() =
    takeIf { !it.startsWith("/") && !it.startsWith("*") }

fun String.memberNameOrNull() = this
    .let {
        val r = substringAfter("fun")
        if (r != it) r
        else it.substringAfter("object")
    }
    .takeIf { result -> result != this }
    ?.substringAfter("`", missingDelimiterValue = "")
    ?.substringBefore("`", missingDelimiterValue = "")
    ?.takeIf { it.isNotBlank() }

fun String.fromJvmNameOrNull() = this
    .takeIf { it.startsWith("@JvmName(") }
    ?.substringAfter("@JvmName(\"")
    ?.substringBefore("\")")

fun String.withIdentifier(fixedMap: MutableMap<String, Int>): String {
    // Add `$#` if name already present in file
    val prevFix = fixedMap[this]
    return this + if (prevFix != null) {
        fixedMap[this] = prevFix + 1
        "\$$prevFix"
    } else {
        fixedMap[this] = 1
        ""
    }
}
