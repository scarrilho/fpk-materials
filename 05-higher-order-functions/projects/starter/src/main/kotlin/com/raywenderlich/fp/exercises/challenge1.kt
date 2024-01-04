package com.raywenderlich.fp.exercises

/*inline fun <A, reified B> Array<A>.map(fn: (A) -> B): Array<B> {
  val result = mutableListOf<B>()
  this.forEach {
      result.add(fn(it))
  }
    return result.toTypedArray()
}*/

inline fun <A, reified B> Array<A>.map(fn: (A) -> B): Array<B> =
   Array(this.size) { fn(this[it])}

fun main() {
    val square = { a: Int -> a * a }
    val toString = { a: Int -> "This is $a" }
    arrayOf(1, 2, 3)
        .map(square)
        .forEach(::println)
    arrayOf(1, 2, 3)
        .map(toString )
        .forEach(::println)
}