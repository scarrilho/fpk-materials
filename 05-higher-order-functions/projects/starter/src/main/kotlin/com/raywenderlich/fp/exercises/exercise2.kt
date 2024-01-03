package com.raywenderlich.fp.exercises

typealias Command = () -> Unit

typealias CommandExecutor = (Command) -> Unit

class MyCommandExecutor : CommandExecutor {
    private val commandHistory = mutableListOf<Command>()

    override fun invoke(command: Command) {
       command.run {
           commandHistory.add(this)
           this()
       }
    }

    fun redo() {
        commandHistory.lastOrNull()?.let {
            it()
        }
    }
}

fun main() {
   val commandExecutor = MyCommandExecutor()

   commandExecutor.invoke { println("Command1") }
   commandExecutor.invoke { println("Command2") }
}