fun day6(){

    val questionData = readFileDirectlyAsText("src/main/resources/day6input")

    println(part1(questionData))
    println(part2(questionData))
}

fun part1 (questionData : String) : Int {

    return questionData.split("\r\n\r\n")
            .map { it.replace("\r\n", "") }
            .map { it.groupBy { it }
                    .size }
            .sum()

}

fun part2 (questionData : String) : Int  {
    return questionData.split("\r\n\r\n")
            .map { it.split("\r\n") }
            .map {
                it.reduce { acc, s -> acc.toList().intersect(s.toList()).toString() }}
            .map { it.replace("[","").replace("]","").replace(",","").replace(" ","").length }.sum()

}
