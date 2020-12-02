import kotlin.math.min
import kotlin.system.measureTimeMillis


fun day2(){

    val inputList = readFileAsLinesUsingBufferedReader("src/main/resources/day2input").map { it.split("-"," ",": ") }

    val passwordPolicyList = inputList.map{ PasswordPolicy(it[0].toInt(),it[1].toInt(),it[2].single()) }
    val passwordList = inputList.map { it[3] }

    var counter  = 0
    passwordPolicyList.forEachIndexed{index, passwordPolicy ->
        if(passwordPolicy.isValid(passwordList[index])){
            counter++
        }
    }

    var officialCounter = 0
    passwordPolicyList.forEachIndexed{index, passwordPolicy ->
        if(passwordPolicy.isOfficialTobagganPolicyValid(passwordList[index])){
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