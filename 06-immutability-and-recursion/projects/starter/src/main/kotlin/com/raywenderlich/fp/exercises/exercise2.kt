package com.raywenderlich.fp.exercises

data class Id1 private constructor(val id: Int) {
    companion object {
        private val ids = mutableMapOf<Int, Id1>()
        fun of(id: Int): Id1 {
            var existingId = ids[id]
            if (existingId == null) {
                existingId = Id1(id)
                ids[id] = existingId
            }
            return existingId
        }
    }
}

fun main() {
    val id1 = Id1.of(1)
    val id2 = id1.copy()
    println("${id1 == id2}")
    println("${id1 === id2}")
}