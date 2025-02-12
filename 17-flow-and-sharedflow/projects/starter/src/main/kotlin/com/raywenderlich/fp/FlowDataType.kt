/*
 *  Copyright (c) 2022 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
 * to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * Not withstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the Software in
 * any work that is designed, intended, or marketed for pedagogical or instructional
 * purposes related to programming, coding, application development, or information
 * technology.  Permission for such use, copying, modification, merger, publication,
 * distribution, sublicensing, creation of derivative works, or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE
 * USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package com.raywenderlich.fp

import com.raywenderlich.fp.model.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun inputStringFlow(question: String = "") = flow {
    val scanner = java.util.Scanner(System.`in`)
    print(question)
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        if (line.isNullOrEmpty()) {
            break
        }
        emit(line)
        print(question)
    }
    scanner.close()
}

fun <A, B> Flow<A>.ap(fn: Flow<(A) -> B>): Flow<B> = flow {
    collect { a ->
        fn.collect { f ->
            emit(f(a))
        }
    }
}

infix fun <A, B> Flow<(A) -> B>.appl(
    a: Flow<A>
) = a.ap(this)

fun main() {
/*    val strLengthFlow = inputStringFlow("Insert a word: ")
        .map { str ->
            str to str.length
        }
    runBlocking {
        strLengthFlow.collect { strInfo ->
            println("${strInfo.first} has length ${strInfo.second}")
        }
    }*/
    val userBuilder = { id: Int ->
        { name: String ->
            { email: String -> User(id, name, email) }
        }
    }

    val userBuilderFlow= flowOf(userBuilder)
    val idFlow = listOf(10, 20, 30).asFlow()
    val nameFlow = listOf("Pipo", "Pippo2", "Pippo3").asFlow()
    val emailFlow = listOf(
        "pippo@pippo.com", "pippo2@pippo.com", "pippo3@pippo.com",
    ).asFlow()
   val userFlow =
       userBuilderFlow appl idFlow appl nameFlow appl emailFlow
    runBlocking {
        userFlow.collect(::println)
    }
}