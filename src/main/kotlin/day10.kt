import GlobalVariable.memo

fun day10()  {
    val adapters = readFileAsLinesUsingBufferedReader("src/main/resources/day10input").map{it.toInt()}


    println (part1(adapters))
    println (part2(adapters))

}

fun part1 (adapters : List<Int>) : Int{

    val sortedAdapters = adapters.sorted()
    val myAdapter = sortedAdapters.last() + 3

    // full chain of data including seat socket and my adapter
    val fullChainOfAdapters = listOf(0) + sortedAdapters + listOf(myAdapter)

    val differenceGroups = fullChainOfAdapters.zipWithNext().map { it.second - it.first }.groupBy { it }

    return (differenceGroups[1] ?: error("")).size * (differenceGroups[3] ?: error("")).size
}

fun part2 (adapters : List<Int>) : Long {
    val sortedAdapters = adapters.sorted()
    val myAdapter = sortedAdapters.last()

    // do not actually need starting and my adapter!
    return generateValidArrangement(sortedAdapters, 0, myAdapter)


}


fun generateValidArrangement(adapters: List<Int>, currentAdapter : Int, maxAdapter : Int) :  Long {

    var count1 = 0L
    var count2 = 0L
    var count3 = 0L

    if (currentAdapter == maxAdapter) {
        return 1
    }

    if (memo.containsKey(currentAdapter)){
        return memo.getValue(currentAdapter)
    }

    if (currentAdapter + 1 in adapters) {
        count1 += generateValidArrangement(adapters.subList(adapters.indexOf(currentAdapter + 1), adapters.lastIndex + 1) , currentAdapter + 1, maxAdapter)
    }
    if (currentAdapter + 2 in adapters) {
        count2 += generateValidArrangement(adapters.subList(adapters.indexOf(currentAdapter + 2),adapters.lastIndex + 1),currentAdapter + 2,maxAdapter )
        }
    if (currentAdapter + 3 in adapters) {count3 += generateValidArrangement(adapters.subList(adapters.indexOf(currentAdapter + 3),adapters.lastIndex + 1),currentAdapter + 3,maxAdapter )
        }

    memo.put(currentAdapter, count1 + count2 + count3)
    return count1 + count2 + count3


}

/*
function factorial (n is a non-negative integer)
    if n is 0 then
            return 1 [by the convention that 0! = 1]
        else if n is in lookup-table then
            return lookup-table-value-for-n
else
        let x = factorial(n – 1) times n [recursively invoke factorial
                with the parameter 1 less than n]
            store x in lookup-table in the nth slot [remember the result of n! for later]
return x
end if
end function

 */