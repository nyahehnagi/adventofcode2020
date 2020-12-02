import java.io.File

fun readFileAsLinesUsingBufferedReader(fileName: String): List<String> {
    return File(fileName).bufferedReader().readLines()
}