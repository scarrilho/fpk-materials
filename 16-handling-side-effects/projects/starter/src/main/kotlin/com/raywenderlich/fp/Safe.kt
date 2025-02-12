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

import com.raywenderlich.fp.lib.compose
import com.raywenderlich.fp.lib.flatMap
import java.util.Scanner

val safeReadName: (World) -> Pair<Result<String>, World> =
    { w: World ->
        try {
            Result.success(Scanner(System.`in`).nextLine()) to World
        } catch (rte: RuntimeException) {
            Result.failure<String>(rte) to World
        }
    }

val safeReadNameError: (World) -> Pair<Result<String>, World> =
    { w: World ->
        Result.failure<String>(
            RuntimeException("Something went wrong!")
        ) to World
    }

val safeReadNameT: WorldT<Result<String>> = safeReadNameError
//val safeReadNameT: WorldT<Result<String>> = safeReadName

val safePrintStringT: (String) -> WorldT<Result<Unit>> =
    { str: String ->
        { w: World ->
            Result.success(Unit) to printString(str)(w)
        }
    }

val safeReadNameM: IO<Result<String>> = IO(safeReadNameT)

val safePrintStringM: (String) -> IO<Result<Unit>> =
    safePrintStringT compose ::IO

fun safeAskNameAndPrintGreetingsIO(): () -> Result<Unit> = {
    safePrintStringM("What's your name? ").bind()
        .flatMap { _ -> safeReadNameM.bind() }
        .flatMap { name ->
            safePrintStringM("Hello $name! \n").bind()
        }
}

fun main() {
    safeAskNameAndPrintGreetingsIO().invoke().fold(
        onSuccess = { _ ->
            // All Good
        },
        onFailure = { ex ->
            println("Error: $ex")
        }
    )
}