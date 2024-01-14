package com.raywenderlich.fp.exercises

typealias ToArray<A, B> = (A) -> Array<B>
// 638
inline infix fun <A, B, reified C> ToArray<A, B>.compose(
    crossinline g: ToArray<B, C>
): ToArray<A, C> = { a ->
    val bArray = this(a)
    val cArray = mutableListOf<C>()
    for (i in bArray) {
        cArray.addAll(g(i))
    }
    cArray.toTypedArray()
}

val fibo = { n: Int ->
    tailrec fun fiboHelper(a: Int, b: Int, fiboN: Int): Int =
        when (fiboN) {
            0 -> a
            1 -> b
            else -> fiboHelper(b, a + b, fiboN - 1)
        }
    fiboHelper(1, 1, n)
}
fun main() {
    val counter= { a: Int -> Array(a) { it } }
    val fiboLength = { n: Int -> Array(n) { fibo(it) } }
    val counterFibo = counter compose fiboLength
    counterFibo(5).forEach { println("$it") }

}