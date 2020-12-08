
import java.util.*

fun day7()  {
    val bagData = readFileAsLinesUsingBufferedReader("src/main/resources/day7input")
    //calculateBagOptions(bagData)
    val bagList = parseBagData(bagData)
    println(getParentBags(bagList,"shiny gold").count())
    println(countBagsInBag(bagList,"shiny gold"))

}
/*
light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.
*/

/*
shiny gold bags contain 2 dark red bags.
dark red bags contain 2 dark orange bags.
dark orange bags contain 2 dark yellow bags.
dark yellow bags contain 2 dark green bags.
dark green bags contain 2 dark blue bags.
dark blue bags contain 2 dark violet bags.
dark violet bags contain no other bags.
 */

fun countBagsInBag(bagList : List<Bag>, bagColour : String): Int {
    var retval = 0
    var sumval = 0

    val parentBag  = bagList.filter { it.bagColour == bagColour }[0]
    if (parentBag.innerBags.isEmpty()){
        return 0
    }
    else{
        parentBag.innerBags.forEach {
            val tmp = it.first * countBagsInBag(bagList, it.second)
            sumval = sumval + it.first
            retval += tmp
        }
    }

    return retval + sumval

}

fun getParentBags(bagList : List<Bag>, bagColour : String): List<String> {

    var parentList : List<String> = emptyList()

     bagList.forEach {
        if (containsBagColour( bagColour,it.innerBags)){
            parentList = parentList + listOf(it.bagColour) + getParentBags(bagList, it.bagColour)
        }
    }

    return parentList.distinct()
}



fun containsBagColour (bagColour : String, innerBags : List<Pair<Int, String>>) : Boolean {
      return innerBags.filter { it.second == bagColour}.count() > 0
}

fun parseBagData(bagData : List<String> ) : List<Bag>{
    return bagData.map { it.replace(" bags contain ", "*") }.map { it.split("*")}
            .map { bagList ->
                Bag(bagList[0], convertSecondPartIntoBags(bagList[1]))
            }

}

fun convertSecondPartIntoBags(s:String):List<Pair<Int, String>> {
    val innerBags = tidyUpBagsString(s).split(", ")
    if (innerBags[0] == "no other") return emptyList()
    return innerBags.map{ bag ->
        Pair(bag.split(" ")[0].toInt(),bag.split(" ")[1] + " " + bag.split(" ")[2])
    }
}

private fun tidyUpBagsString(rawString: String) = rawString.replace(".","").replace(" bags","").replace(" bag","")


data class Bag (val bagColour : String, val innerBags : List<Pair<Int, String>>)


/*
fun calculateBagOptions(bagData: List<String>) {
    bagData.map { bagString ->
        if (containsBagColour(bagString, "shiny gold")) {
            //options.plus(parentBagColour())
        }
    }
}


fun containsBagColour(bagString: String, bagColour: String) = bagString.split("contain")[1].contains(bagColour)

fun parentBagColour(bagString: String) = bagString.split(" bags contain")[0]
*/

