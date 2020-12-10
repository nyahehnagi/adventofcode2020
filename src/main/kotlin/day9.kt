fun day9()  {
    val xmasFeed = readFileAsLinesUsingBufferedReader("src/main/resources/day9input").map{it.toLong()}

    val preamble = 25
    val invalidNumber = findFirstNumberThatIsNotSumOfNNumbersBeforeIt(xmasFeed,preamble)

    println(invalidNumber)
    println(findSumOfLowestAndHighestNumbersInContagiousRange(xmasFeed,preamble))

}
// URGHHHH
fun findSumOfLowestAndHighestNumbersInContagiousRange (numberFeed : List<Long>,preamble : Int ) : Long{

    val invalidNumber = findFirstNumberThatIsNotSumOfNNumbersBeforeIt(numberFeed,preamble)

    numberFeed.forEachIndexed { indexAsc, ascending ->
        var indexDesc = 0
        val reversedList = numberFeed.asReversed()
        while( reversedList[indexDesc] > ascending)
        {
            val sub = numberFeed.subList(indexAsc, numberFeed.lastIndex - indexDesc)
            if (sub.sum() == invalidNumber) {
                return sub.sorted()[0] + sub.max()!!
            }
            indexDesc ++
        }
    }

    return 0L
}

fun List<Long>.findRangeThatSumsTo(number: Long): List<Long> {
    for (i in indices) for (j in indices.reversed()) {
        if (j < i) break
        val sub = subList(i, j)
        if (sub.sum() == number) return sub
    }
    error("not found")
}


fun findFirstNumberThatIsNotSumOfNNumbersBeforeIt (numberFeed : List<Long>,preamble : Int ) : Long {

    val noPairFound  = Pair(-1L,-1L)

    val listAfterPreamble = numberFeed.subList(preamble,numberFeed.lastIndex)

    numberFeed.windowed(preamble).mapIndexed { index, list ->
        if (findTwoEntriesThatSumToValue(list,listAfterPreamble[index]) == noPairFound) {return listAfterPreamble[index]}
    }
    return 0
}

//Lifted from day1 and changed to Longs. Maybe I should try and make this function generic
fun findTwoEntriesThatSumToValue (listOfEntries : List<Long>, sumValue : Long) : Pair<Long,Long> {
    // using the sorting and walking inward algorithm
    val sortedAscending = listOfEntries.sorted()
    var lowestNumber = 0
    var highestNumber = sortedAscending.lastIndex

    while (lowestNumber < highestNumber) {
        var sum = sortedAscending[lowestNumber] + sortedAscending[highestNumber]
        if (sum == sumValue) return Pair(sortedAscending[lowestNumber], sortedAscending[highestNumber])
        else if (sum <  sumValue) lowestNumber++
        else highestNumber--
    }

    //No match
    return Pair(-1,-1)
}