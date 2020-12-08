fun day8()  {
    val bootCode = readFileAsLinesUsingBufferedReader("src/main/resources/day8input")
    println(getAccumulatedValueForOnePassOfInfiniteLoop(bootCode).accumulator)
    println(getAccumulatedValueByAutoFixingCode(bootCode))

}

/*
nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6
 */

fun getAccumulatedValueByAutoFixingCode( bootCode : List<String>) : Int{

    //get the list of instructions called by an infinite loop to reduce the amount of effort needed
    val instructionsLogInfiniteLoop = getAccumulatedValueForOnePassOfInfiniteLoop(bootCode).instructionsLog
    // reduce to nop and jmp instructions
    val reducedInstructions = instructionsLogInfiniteLoop.filter { it.instruction is Instruction.Jump || it.instruction is Instruction.NoOp }

    reducedInstructions.forEach {
        // swap nop or jmp
        val amendedBootCode = bootCode.mapIndexed { index, instruction -> if (index == it.instructionIndex) {swapInstruction(instruction)} else instruction  }
        // test to see if the code is fixed
        val processorState = runBootCode(amendedBootCode)
        if (processorState.currentInstructionIndex > amendedBootCode.lastIndex){
            return processorState.accumulator}
    }

    return 0
}

fun runBootCode (bootCode : List<String>) : ProcessorState {
    var processorState = initialiseProcessorState()
    val instructions = bootCode.map { Instruction(it) }

    do{
        processorState = getNextInstruction(instructions,processorState).executeInstruction(processorState)
    }
    while    (processorState.currentInstructionIndex !in processorState.instructionsLog.map{it.instructionIndex}
            && (processorState.currentInstructionIndex <=  bootCode.lastIndex)    )

    return processorState
}

fun swapInstruction(ins : String): String{
    if (ins.contains("nop")){return ins.replace("nop","jmp")}
    if (ins.contains("jmp")){return ins.replace("jmp","nop")}
    return ins

}

fun getAccumulatedValueForOnePassOfInfiniteLoop( bootCode : List<String>) : ProcessorState{

    var processorState = initialiseProcessorState()
    val instructions = bootCode.map { Instruction(it) }

    do{
        processorState = getNextInstruction(instructions,processorState).executeInstruction(processorState)
    }
    while (processorState.currentInstructionIndex !in processorState.instructionsLog.map{it.instructionIndex})

    return processorState
}

fun initialiseProcessorState() = ProcessorState(0,0, emptyList())
fun getNextInstruction(instructions : List<Instruction>, processorState : ProcessorState) = instructions[processorState.currentInstructionIndex]

sealed class Instruction {
    companion object {
        operator fun invoke (instruction : String): Instruction {
            val parts = instruction.split(" ")
            val instructionType = parts[0]
            val instructionValue = calculateInstructionValue(parts[1])

            return when (instructionType){
                "nop" -> Instruction.NoOp
                "acc" -> Instruction.Accumulate(instructionValue)
                "jmp" -> Instruction.Jump(instructionValue)
                else -> Instruction.Error
            }
        }

        private fun calculateInstructionValue(value : String) :Int{
            return if (value.first() == '+'){
                value.substring(1).toInt()
            } else{
                value.substring(1).toInt() * -1
            }
        }
    }

    abstract fun executeInstruction (currentState: ProcessorState ) : ProcessorState

    object Error : Instruction(){
        override fun executeInstruction(currentState: ProcessorState): ProcessorState {
            return currentState
        }
    }

    object NoOp : Instruction() {
        override fun executeInstruction(currentState: ProcessorState): ProcessorState {
            return ProcessorState(
                    currentState. currentInstructionIndex +1,
                    currentState.accumulator,
                    currentState.instructionsLog + listOf(InstructionLog(currentState.currentInstructionIndex, this))
            )
        }
    }
    class Accumulate(private val acc: Int) : Instruction(){
        override fun executeInstruction(currentState: ProcessorState): ProcessorState {
            return ProcessorState(
                    currentState. currentInstructionIndex + 1,
                    currentState.accumulator + acc,
                    currentState.instructionsLog + listOf(InstructionLog(currentState.currentInstructionIndex, this))
            )
        }
    }

    class Jump(val moveBy: Int) : Instruction() {
        override fun executeInstruction(currentState: ProcessorState): ProcessorState {
            return ProcessorState(
                    currentState. currentInstructionIndex + moveBy,
                    currentState.accumulator,
                    currentState.instructionsLog + listOf(InstructionLog(currentState.currentInstructionIndex, this))
            )
        }
    }

}

data class ProcessorState (val currentInstructionIndex : Int, val accumulator: Int, val instructionsLog : List<InstructionLog>)
data class InstructionLog(val instructionIndex : Int,val instruction : Instruction)


