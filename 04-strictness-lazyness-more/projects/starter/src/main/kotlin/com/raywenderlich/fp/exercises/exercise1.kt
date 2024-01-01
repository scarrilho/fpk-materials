package com.raywenderlich.fp.exercises

import kotlin.math.pow
import kotlin.math.sqrt


val distance = {
    x1: Double, y1: Double, x2: Double, y2: Double ->
    sqrt((double(x2 - x1) + double(y2 - y1)))
}

fun double(x: Double): Double = x * x

typealias Point = Pair<Double, Double>

val distanceLambdaWithPairs = { p1: Point, p2: Point ->
    val sqr1 = (p1.first - p2.first).pow(2.0)
    val sqr2 = (p1.second - p2.second).pow(2.0)
    sqrt(sqr1 + sqr2)

}

fun main() {
    println(distance(0.0, 0.0, 1.0, 1.0))
    println(distanceLambdaWithPairs(0.0 to 0.0, 1.0 to 1.0))
}