package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.pipe

fun <T, S> Iterable<T>.iterableFold(
    start: S,
    combineFun: (S, T) -> S
): S {
    tailrec fun helper(iterator: Iterator<T>, acc: S): S {
        if (!iterator.hasNext()) {
            return acc
        }
        return helper(iterator, combineFun(acc,
            iterator.next()))
    }
    return helper(iterator(), start)
}

fun <T, S> Iterable<T>.iterableFoldRight(
    start: S,
    combineFun: (T, S) -> S
): S {
    fun helper(iterator: Iterator<T>): S {
        if (!iterator.hasNext()) {
            return start
        }
        return combineFun(iterator.next(), helper(iterator))
    }
    return helper(iterator())
}

fun main() {
    "supercalifragilisticexpialidocious".asIterable()
        .iterableFoldRight(StringBuilder()) { item, acc ->
            acc.append(item)
            acc
        } pipe ::println

    "supercalifragilisticexpialidocious".asIterable()
        .iterableFold(StringBuilder()) { acc, item ->
            acc.append(item)
            acc
        } pipe ::println
}