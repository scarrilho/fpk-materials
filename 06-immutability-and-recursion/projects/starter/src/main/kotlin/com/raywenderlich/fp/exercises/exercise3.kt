package com.raywenderlich.fp.exercises

fun moveDisks(disks: Int, from: Int, to: Int, using: Int) {
    if (disks > 0) {
        moveDisks(disks - 1, from, using, to)
        println("Moving $disks from $from to $to")
        moveDisks(disks - 1, using, to, from)
    }
}

fun main() {
    moveDisks(disks = 4,  from = 1, to = 3, using = 2)
}