package com.raywenderlich.fp.exercises

class Id private constructor(val id: Int) {
    companion object {
        private val ids = mutableMapOf<Int, Id>()
        fun of(id: Int): Id {
            var existingId = ids[id]
            if (existingId == null) {
                existingId = Id(id)
                ids[id] = existingId
            }
            return existingId
        }
    }
}

fun main() {
    val id1 = Id.of(1)
    val id2 = Id.of(1)
    val id3 = Id.of(2)
    val id4 = Id.of(2)
    println("${id1 === id2}")
    println("${id1 === id2}")
    println("${id1 === id3}")
    println("${id3 === id4}")
}