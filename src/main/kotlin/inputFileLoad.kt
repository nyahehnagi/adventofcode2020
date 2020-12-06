import java.io.File

fun readFileAsLinesUsingBufferedReader(fileName: String): List<String> {
    return File(fileName).bufferedReader().readLines()
}

fun readFileDirectlyAsText(fileName: String): String
        = File(fileName).readText(Charsets.UTF_8)