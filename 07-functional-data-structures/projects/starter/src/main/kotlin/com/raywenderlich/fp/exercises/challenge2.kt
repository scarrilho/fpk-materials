package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.FList
import com.raywenderlich.fp.filter
import com.raywenderlich.fp.match

typealias Predicate<T> = (T) -> Boolean

fun <T> FList<T>.fastFirstWhen(predicate: Predicate<T>): T? = match(
    whenNil = { null },
    whenCons = { head, tail ->
        if (predicate(head)) {
            head
        } else {
            tail.fastFirstWhen(predicate)
        }
    }
)

fun <T> FList<T>.firstWhen(predicate: Predicate<T>): T? =
    filter(predicate).first()

/*fun <T> FList<T>.lastWhen(predicate: Predicate<T>): T? {
    fun go(fList: FList<T>, lastFound: T?): T? = fList.match(
        whenNil = { lastFound },
        whenCons = { head, tail ->
            if (predicate(head)) {
                go(tail, head)
            } else {
                go(tail, lastFound)
            }
        }
    )

    return go(this, null)
}*/

fun <T> FList<T>.lastWhen(predicate: Predicate<T>): T? =
    filter(predicate).last()


fun main() {
/*    val rule: Predicate<Int> = { value -> value > 3}

    println(FList.empty<Int>().firstWhen(rule))
    println(FList.empty<Int>().lastWhen(rule))
    println(FList.of(1).firstWhen(rule))
    println(FList.of(1).lastWhen(rule))
    println(FList.of(1, 2, 3, 4, 5, 6, 7, 8, 9).firstWhen(rule))
    println(FList.of(1, 2, 3, 4, 5, 6, 7, 8, 9).lastWhen(rule))*/

    val isEven: Predicate<Int> = { a: Int -> a % 2 == 0 }
    println(FList.of(1, 2, 3, 4, 5, 6).firstWhen(isEven))
    println(FList.of(1, 2, 3, 4, 5, 6).lastWhen(isEven))
    println(FList.of(1, 2, 3, 4, 5, 6).fastFirstWhen(isEven))

}