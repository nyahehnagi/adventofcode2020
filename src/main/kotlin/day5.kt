fun day5(){

    val boardingPasses = readFileAsLinesUsingBufferedReader("src/main/resources/day5input")

    println(calculateHighestID(calculateIDs(boardingPasses)))
    println(calculateMySeat(calculateIDs(boardingPasses)))
}

fun calculateHighestID(IDs : List<Int>) = IDs.last()

fun calculateMySeat(IDs : List<Int>) =
        IDs.zipWithNext()
            .first { (first, second) -> second != first + 1 }.first + 1



fun calculateIDs (boardingPasses : List<String>) =
        boardingPasses.map { it.replace(Regex("[RB]"), "1").replace(Regex("[FL]"), "0") }
        .map { it.toInt(2) }
        .sorted()