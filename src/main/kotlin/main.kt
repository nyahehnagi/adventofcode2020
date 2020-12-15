import java.io.File
import kotlin.system.measureTimeMillis

object GlobalVariable {
    var memo = mutableMapOf<Int,Long>()
    var tracker = mutableMapOf<Int,Pair<Int,Int>>()
}

fun main(){
    day15()
}


