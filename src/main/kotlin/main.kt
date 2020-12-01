import java.io.File


fun main(){
    day1()
}

fun day1(){
    val inputList = readFileAsLinesUsingBufferedReader("src/main/resources/day1input")
    val inputListAsInt = inputList.map{it.toInt()}
    val matchingPair = findTwoEntriesThatSumToValue(inputListAsInt,2020)
    println(matchingPair.first.times(matchingPair.second))

    val  matchingTriple = findThreeEntriesThatSumToValue(inputListAsInt, 2020)
    println(matchingTriple.first.times(matchingTriple.second.times(matchingTriple.third)))
}

fun readFileAsLinesUsingBufferedReader(fileName: String): List<String> {
    return File(fileName).bufferedReader().readLines()
}