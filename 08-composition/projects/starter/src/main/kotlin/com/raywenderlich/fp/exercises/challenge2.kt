package com.raywenderlich.fp.exercises

import com.raywenderlich.fp.compose

fun <A> (() -> A).addUnit(): (Unit) -> A = { unit: Unit -> this() }

fun <A> ((Unit) -> A).removeUnit(): () -> A = { this(Unit)}

val three = { 3 }
val unitToThree = { a: Unit -> 3}
fun main() {
    val double = { a: Int -> a * 2 }
    val comp2 = unitToThree compose double
    val comp1 = three.addUnit() compose double
}