

fun day3(){

    val forestMap = readFileAsLinesUsingBufferedReader("src/main/resources/day3input")

    val slopePart1 = listOf(Pair(3,1))
    val slopePart2 = listOf(Pair(1,1), Pair(3,1), Pair(5,1),Pair(7,1),Pair(1,2))

    println("Part 1 Trees Hit (Product) : " + calculateTreesHitProduct(forestMap,slopePart1))
    println("Part 2 Trees Hit (Product) : " + calculateTreesHitProduct(forestMap,slopePart2))

}


fun calculateTreesHitProduct (forestMap: List<String>, slopeList : List<Pair<Int,Int>> ) : Int{
    val slopeLength = forestMap.size
    val routes = slopeList.map { generateRoute(it,slopeLength) }
    return routes.map{ route ->
        route.map { isTreeByLocation(forestMap,it) }
                .filter { it }
                .count() }
            .reduce { acc, i -> acc * i }
}

fun generateRoute(slope : Pair<Int,Int>, slopeLength : Int) : List<Pair<Int,Int>> {
    return generateSequence(slope) {
        if (it.second + slope.second < slopeLength) {
            Pair(it.first + slope.first,it.second + slope.second)
            }
            else null }.toList()
}

fun isTreeByLocation(forestMap : List<String>, location : Pair<Int, Int>) : Boolean{
    val mapWidth = forestMap[location.second].length
    val xValue = forestMap[location.second][location.first % mapWidth]

    if (xValue == '#') return true
    return false
}