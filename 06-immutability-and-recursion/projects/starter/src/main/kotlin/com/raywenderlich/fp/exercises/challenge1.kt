package com.raywenderlich.fp.exercises

fun recAddMulti5(list: List<Int>): Int {
    tailrec fun loop(i: Int, sum: Int): Int = when {
        i == list.size -> sum
        list[i] % 5 == 0 -> loop(i + 1, sum + list[i])
        else -> loop(i + 1, sum)
    }
    return loop(0, 0)
}