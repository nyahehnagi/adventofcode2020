import kotlin.math.min


fun day2(){
    val inputList = readFileAsLinesUsingBufferedReader("src/main/resources/day2input").map { it.split(" ") }
    val minMaxList = inputList.map { it[0].split("-") }
    val codeList = inputList.map { it[1].first()}
    val passwordList = inputList.map { it[2]}

    val passwordPolicyList : MutableList<PasswordPolicy> = mutableListOf()

    minMaxList.forEachIndexed{index, element ->
        passwordPolicyList.add(PasswordPolicy(element[0].toInt(), element[1].toInt(),codeList[index]))
    }

    var counter  = 0
    passwordPolicyList.forEachIndexed{index, element ->
        if(element.isValid(passwordList[index])){
            counter++
        }
    }

    var officialCounter = 0
    passwordPolicyList.forEachIndexed{index, element ->
        if(element.isOfficialTobagganPolicyValid(passwordList[index])){
            officialCounter++
        }
    }


    println(counter)
    println(officialCounter)
}


class PasswordPolicy(val minimumCount: Int, val maximumCount: Int, val character: Char) {


    fun isValid(password: String) : Boolean{
      val characterCount = password.filter { it == character }.count()
      return (characterCount in minimumCount..maximumCount)
    }

    fun isOfficialTobagganPolicyValid(password: String) : Boolean {
        val firstCharacter = password[minimumCount - 1].toString()
        val secondCharacter = password[maximumCount - 1].toString()

        if (firstCharacter.filter { it == character }.count() + secondCharacter.filter { it == character }.count() == 1)
            return true

        return false
    }
}