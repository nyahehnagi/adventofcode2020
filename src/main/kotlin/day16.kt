
fun day16(){

    val ticketData = readFileDirectlyAsText("src/main/resources/day16input").split("\r\n\r\n")
    val rawRulesData = ticketData[0].split("\r\n")
    val myTicket = ticketData[1].split("\r\n").drop(1)
    val nearbyTickets = ticketData[2].split("\r\n").drop(1)

    part1(rawRulesData,nearbyTickets)

}

fun part1(rawRulesData : List<String>, nearbyTickets : List<String>){

    val ticketRules : TicketRules = TicketRules(rawRulesData)
    val nearbyTickets : NearbyTickets = NearbyTickets(nearbyTickets)

    println(nearbyTickets.sumOfInvalidFields(ticketRules))
}
/*
class: 1-3 or 5-7
row: 6-11 or 33-44
seat: 13-40 or 45-50

your ticket:
7,1,14

nearby tickets:
7,3,47
40,4,50
55,2,20
38,6,12
 */

class NearbyTickets(rawTicketData : List<String>){
    val tickets : MutableList<Ticket> = rawTicketData.map { Ticket(it) }.toMutableList()

    fun sumOfInvalidFields (rules : TicketRules) : Int{
        return tickets.flatMap { it.data.filterNot { rules.matchRules(it) } }.sum()
    }

}

class Ticket(rawTicket : String){
    val data : List <Int> = rawTicket.split(",").map { it.toInt() }
}

class TicketRules(rawRulesData : List<String>){
    val rules :  List<TicketRule> = rawRulesData.map { TicketRule(it) }

    fun matchRules (value : Int) : Boolean{
        return rules.any{ rule -> rule.matchRule(value)}
    }
}

class TicketRule(rawRule : String){
    lateinit var fieldDescription : String
    lateinit var range1 : IntRange
    lateinit var range2 : IntRange


    init {
        val regexRules = Regex("([A-Za-z ]+): (([0-9]+)-([0-9]+)) or (([0-9]+)-([0-9]+))")
        val matchResult = regexRules.find(rawRule)
        if (matchResult != null) {
            this.fieldDescription = matchResult.groups[1]?.value.toString()
            this.range1 = IntRange(matchResult.groups[3]?.value.toString().toInt(),matchResult.groups[4]?.value.toString().toInt() )
            this.range2 = IntRange(matchResult.groups[6]?.value.toString().toInt(),matchResult.groups[7]?.value.toString().toInt() )
        }
    }

    fun matchRule (value : Int) : Boolean {
        return (range1.any { x -> x == value } || range2.any { y -> y == value })
    }
}
