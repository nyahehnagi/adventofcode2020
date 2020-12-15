import GlobalVariable.tracker


fun day15()  {
    val testList = mutableMapOf(1 to 0, 2 to 3, 3 to 6)
    val testList2 = mutableMapOf(1 to 1,2 to 2,3 to 3)
    val startingList = mutableMapOf(1 to 5,2 to 1, 3 to 9,4 to 18, 5 to 13, 6 to 8, 7 to 0)

    val countTo : Int = 2020
    val countTo2 : Int = 30000000

    println(part1(testList, countTo))
    println(part1(testList2, countTo))

    println (part1(startingList, countTo))
    println(part1(startingList, countTo2))


}

fun part1 (numbers : MutableMap<Int, Int>, countTo : Int) : Int? {

    val startingValue = numbers.size + 1
    //initialise tracker
    initTracker(numbers)

    for (turn in startingValue..countTo) {
        numbers[turn] = determineNextValue (numbers)
    }

    return numbers[countTo]
}

fun initTracker(numbers : MutableMap<Int, Int>){
    tracker.clear()
    numbers.forEach{tracker[it.value] = Pair(it.key,it.key)}
}

fun seenBefore(number: Int, indexOfLast : Int) : Int{
     if (tracker.containsKey(number)){
         tracker[number] = tracker[number]?.let { Pair(it.second,indexOfLast) }!!
         return tracker[number]!!.second - tracker[number]!!.first
     }

    tracker[number] = Pair(indexOfLast,indexOfLast)

    return tracker[number]!!.second - tracker[number]!!.first
}

fun determineNextValue(numberList : MutableMap<Int, Int>) : Int {
    val lastNumberSpoken = numberList[numberList.size]
    val indexOfLastNumber = numberList.size

    return lastNumberSpoken?.let { seenBefore(it, indexOfLastNumber) }!!

}


