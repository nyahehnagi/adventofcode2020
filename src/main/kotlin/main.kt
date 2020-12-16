import java.io.File
import kotlin.system.measureTimeMillis

object GlobalVariable {
    var memo = mutableMapOf<Int,Long>()
}

fun main(){
    day16()
}


