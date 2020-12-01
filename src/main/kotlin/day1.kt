
fun findTwoEntriesThatSumToValue (listOfEntries : List<Int>, sumValue : Int) : Pair<Int,Int> {
    // using the sorting and walking inward algorithm
    val sortedAscending = listOfEntries.sorted()
    var leftHandSide = 0
    var rightHandSide = sortedAscending.lastIndex

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