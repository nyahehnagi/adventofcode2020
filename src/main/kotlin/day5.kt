fun day5(){

    val boardingPasses = readFileAsLinesUsingBufferedReader("src/main/resources/day5input")

    println(calculateHighestID(calculateIDs(boardingPasses)))
    println(calculateMySeat(calculateIDs(boardingPasses)))
}

// take the last ID in the list as sorted ascending
fun calculateHighestID(IDs : List<Int>) = IDs.last()

// Pair up the seats and compare to find missing seat
fun calculateMySeat(IDs : List<Int>) =
        IDs.zipWithNext()
            .first { (first, second) -> second != first + 1 }.first + 1


//turn each row into a binary number and convert to Integer
fun calculateIDs (boardingPasses : List<String>) =
        boardingPasses.map { it.replace(Regex("[RB]"), "1").replace(Regex("[FL]"), "0") }
        .map { it.toInt(2) }
        .sorted()