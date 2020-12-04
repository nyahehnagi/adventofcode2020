
fun day4(){

    val passportData = readFileAsLinesUsingBufferedReader("src/main/resources/day4input")

    println(validatePassportCountPart1(parsePassportData(passportData)))
    println(validatePassportCountPart2(parsePassportData(passportData)))
}

fun validatePassportCountPart1(passportData : List<Map<String,String>>): Int{
    val validKeys = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    return passportData.count { map -> validKeys.all { it in map } }
}

//used the net to learn more about the usage of let/run
fun validatePassportCountPart2(passportData : List<Map<String,String>>): Int{
    val validKeys = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    return passportData.count { map -> validKeys.all {
                map["byr"]?.toIntOrNull()?.let { it in 1920..2002 } == true &&
                map["iyr"]?.toIntOrNull()?.let { it in 2010..2020 } == true &&
                map["eyr"]?.toIntOrNull()?.let { it in 2020..2030 } == true &&
                map["hgt"]?.run {
                    endsWith("cm") && dropLast(2).toIntOrNull()?.let { it in 150..193 } == true ||
                            endsWith("in") && dropLast(2).toIntOrNull()?.let { it in 59..76 } == true
                } == true &&
                map["hcl"]?.run {
                    length == 7 && first() == '#' && drop(1).all { it in "0123456789abcdef" }
                } == true &&
                map["ecl"] in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") &&
                map["pid"]?.run {
                    length == 9 && all { it in "0123456789" }
                } ==true
        }
    }

}

fun parsePassportData (passportData : List<String>) : List<Map<String,String>>{

    val passportList = mutableListOf<Map<String,String>>()
    var passportMap = mutableMapOf<String,String>()

    passportData.forEach { line ->
        if (line.isEmpty()){
            passportList.add(passportMap)
            passportMap = mutableMapOf()
        } else{
            line.split(" ").map { it.split(":") }
                    .forEach { passportMap[it[0]] = it[1] }
        }
    }
    passportList.add(passportMap)

    return passportList
}
