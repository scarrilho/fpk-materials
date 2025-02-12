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

package com.raywenderlich.fp.optional

import com.raywenderlich.fp.applicative.ResultAp
import com.raywenderlich.fp.applicative.errorMap
import com.raywenderlich.fp.applicative.flatMap
import com.raywenderlich.fp.applicative.successMap
import com.raywenderlich.fp.lib.*
import com.raywenderlich.fp.model.ScoredShow
import com.raywenderlich.fp.tools.fetchers.TvShowFetcher
import com.raywenderlich.fp.tools.parser.TvShowParser
import kotlinx.serialization.SerializationException
import java.io.IOException

fun fetchTvShowOptional(
    query: String
): Optional<String> = try {
    Optional.lift(TvShowFetcher.fetch(query))
} catch (ioe: IOException) {
    Optional.empty()
}

/** Invokes the parser returning an Optional */
fun parseTvShowString(
    json: String
): Optional<List<ScoredShow>> =
    try {
        Optional.lift((TvShowParser.parse(json)))
    } catch (e: SerializationException){
        Optional.empty()
    }

fun fetchAndParseTvShow(query: String) =
    fetchTvShowOptional(query).flatMap(::parseTvShowString)

fun fetchTvShowEither(
    query: String
): Either<IOException, String> = try {
    Either.right(TvShowFetcher.fetch(query))
} catch (ioe: IOException) {
    Either.left(ioe)
}

/** Invokes the parser returning an Optional */
fun parseTvShowEither(
    json: String
): Either<SerializationException, List<ScoredShow>> = try {
    Either.right(TvShowParser.parse(json))
} catch (e: SerializationException) {
    Either.left(e)
}

fun fetchAndParseTvShowEither(query: String) =
    fetchTvShowEither(query)
        .flatMap(::parseTvShowEither)

fun fetchTvShowResultAp(
    query: String
): ResultAp<IOException, String> = try {
    ResultAp.success(TvShowFetcher.fetch(query))
} catch (ioe: IOException) {
    ResultAp.error(ioe)
}

fun parseTvShowResultAp(
    json: String
): ResultAp<SerializationException, List<ScoredShow>> = try {
    ResultAp.success(TvShowParser.parse(json))
} catch (e: SerializationException) {
    ResultAp.error(e)
}

fun fetchAndParseTvShowResultAp(query: String) =
    fetchTvShowResultAp(query).flatMap(::parseTvShowResultAp)

fun main() {
    fetchAndParseTvShow("Big Bang Theory")
        .getOrDefault(emptyList<ScoredShow>()) pipe ::println
    fetchAndParseTvShowEither("Big Bang Theory")
        .leftMap {
            println("Error: $it")
        }
        .rightMap {
            println("Result: $it")
        }
//    fetchAndParseTvShowResultAp("Big Bang Theory") pipe ::println
    fetchAndParseTvShowResultAp("Big Bang Theory")
        .errorMap {
            println("Error2: $it")
            it
        }
        .successMap {
            println("Result: $it")
        }
}