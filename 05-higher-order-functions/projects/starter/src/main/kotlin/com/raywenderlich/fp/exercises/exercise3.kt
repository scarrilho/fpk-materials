package com.raywenderlich.fp.exercises

fun interface Reader {
    fun readChar(): Char?

    fun readString(): String {
        val str = StringBuilder()
        var ch: Char? = readChar()

        while (ch != null) {
            str.append(ch)
            ch = readChar()
        }

      return str.toString()
    }
}

class MyReader(val str: String) : Reader {
    private var pos = 0

    override fun readChar(): Char? =
        if (pos < str.length) str[pos++] else null
}

fun main() {
    /*val input = MyReader("This is a string")
    println(input.readString())*/

    val inputString = "This is a String!"
    var pos = 0
    var pos2 = 0
    val input = Reader {
        if (pos < inputString.length) inputString[pos++] else null
    }
    val input2 = Reader {
        if (pos2 < inputString.length) inputString[pos2++] else null
    }
    println(input.readString())
    println(input2.readString())
}