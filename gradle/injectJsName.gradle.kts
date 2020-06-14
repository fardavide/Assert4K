@file:Suppress("PropertyName")

/*
 * Inject @JsName annotation for function names that are not supported on Js
 * Author: Davide Farella
 */

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

for (file in files) {
    val tmp = File.createTempFile("tmp_", null, file.parentFile)

    tmp.bufferedWriter().use { writer ->

        var lastLine: String? = null
        // Keep track of name of annotated function with a count for each name, for avoid to have duplicated names,
        // since Js doesn't support function with different parameters but same name
        val fixedMap = mutableMapOf<String, Int>()
        file.forEachLine { line ->
            val lastLineTrim = lastLine?.trim()

            // Skip @JsName since we are goin' to inject it
            if (lastLineTrim?.startsWith("@JsName") == false)
                writer.appendln(lastLine)

            line.trim()
                // Skip comments
                .takeIf { !it.startsWith("/") && !it.startsWith("*") }
                ?.substringAfter("fun", missingDelimiterValue = "")
                ?.substringAfter("`", missingDelimiterValue = "")
                ?.substringBefore("`", missingDelimiterValue = "")
                ?.takeIf { it.isNotBlank() }
                ?.let {
                    // Reuse @JvmName if any
                    val origName = lastLineTrim
                        ?.takeIf { last -> last.startsWith("@JvmName(") }
                        ?.substringAfter("@JvmName(\"")
                        ?.substringBefore("\")")
                        ?: it
                    // Fix the name
                    val fixedName = origName
                        .replace(" ", "_")
                        .replace("'", "_")
                    // Add `$#` if name already present in file
                    val prevFix = fixedMap[fixedName]
                    val extra = if (prevFix != null) {
                        fixedMap[fixedName] = prevFix + 1
                        "\$$prevFix"
                    } else {
                        fixedMap[fixedName] = 1
                        ""
                    }
                    val wrap = line.takeWhile { c -> c == ' ' }
                    writer.appendln("$wrap@JsName(\"$fixedName$extra\")")
                }

            lastLine = line
        }

        writer.appendln(lastLine)
    }

    tmp.renameTo(file)
}
