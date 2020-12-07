fun day1(){
    val inputList = readFileAsLinesUsingBufferedReader("src/main/resources/day1input")
    val inputListAsInt = inputList.map{it.toInt()}

    val matchingPair = findTwoEntriesThatSumToValue(inputListAsInt,2020)
    println(matchingPair.first.times(matchingPair.second))

    val  matchingTriple = findThreeEntriesThatSumToValue(inputListAsInt, 2020)
    println(matchingTriple.first.times(matchingTriple.second.times(matchingTriple.third)))
}

fun findTwoEntriesThatSumToValue (listOfEntries : List<Int>, sumValue : Int) : Pair<Int,Int> {
    // using the sorting and walking inward algorithm
    val sortedAscending = listOfEntries.sorted()
    var leftHandSide = 0
    var rightHandSide = sortedAscending.lastIndex
    // change names of the lefthand side etc. So change to lower and higher number.

    while (leftHandSide < rightHandSide) {
        var sum = sortedAscending[leftHandSide] + sortedAscending[rightHandSide]
        if (sum == sumValue) return Pair(sortedAscending[leftHandSide], sortedAscending[rightHandSide])
        else if (sum <  sumValue) leftHandSide++
        else rightHandSide--
    }

    //No match
    return Pair(-1,-1)
}

fun findThreeEntriesThatSumToValue(listOfEntries : List<Int>, sumValue : Int) : Triple<Int,Int,Int>{

    val workingList = mutableListOf<Int>()

    for (i in 0 until listOfEntries.lastIndex - 1){
        val currentSum = sumValue -  listOfEntries[i]
        for (j in i+1 until listOfEntries.lastIndex){
             if (workingList.contains(currentSum - listOfEntries[j])){
                 // found the three numbers
                 return Triple(listOfEntries[i], listOfEntries[j],currentSum - listOfEntries[j])
             }
            workingList.add(listOfEntries[j])
        }
    }

    // No triplet was found
    return Triple (-1,-1,-1)
}