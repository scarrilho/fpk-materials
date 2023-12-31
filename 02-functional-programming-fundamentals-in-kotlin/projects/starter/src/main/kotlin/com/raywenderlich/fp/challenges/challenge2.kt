package com.raywenderlich.fp.challenges

val oddIntSet: (Int) -> Boolean = { a: Int -> a % 2 == 0}

val multipleOf37: (Int) -> Boolean = { a: Int -> a % 37 == 0}

fun <A> intersection(set1: (A) -> Boolean, set2: (A) -> Boolean): (A) -> Boolean =
    { a: A -> set1(a) && set2(a)}

fun <A> union(set1: (A) -> Boolean, set2: (A) -> Boolean): (A) -> Boolean =
    { a: A -> set1(a) || set2(a)}


fun main() {
    val oddMultipleOf37Union =
        union<Int>(oddIntSet, multipleOf37 )
    val oddMultipleOf37Intersection =
        intersection(oddIntSet, multipleOf37)

    println("1   is in union ${oddMultipleOf37Union(1)}")
    println("37  is in union ${oddMultipleOf37Union(37)}")
    println("74  is in union ${oddMultipleOf37Union(74)}")
    println("100 is in union ${oddMultipleOf37Union(100)}")

    println("1   is in intersection ${oddMultipleOf37Intersection(1)}")
    println("37  is in intersection ${oddMultipleOf37Intersection(37)}")
    println("74  is in intersection ${oddMultipleOf37Intersection(74)}")
    println("100 is in intersection ${oddMultipleOf37Intersection(100)}")
}
