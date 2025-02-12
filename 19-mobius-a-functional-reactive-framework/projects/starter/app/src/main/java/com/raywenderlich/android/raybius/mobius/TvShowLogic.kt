/*
 * Copyright (c) 2022 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.raybius.mobius

import com.spotify.mobius.Next

/** The logic for the Raybious app */
val tvShowLogic: TvShowUpdate = object : TvShowUpdate {
  override fun update(model: TvShowModel, event: TvShowEvent): Next<TvShowModel, TvShowEffect> =
    when (event) {
      is InputTextChanged -> Next.next(
        model.copy(
          searchEnabled = event.text.length >= 3,
          inputText = event.text
        )
      )
      is SearchButtonClicked -> Next.next(
        model.copy(loading = true),
        setOf(SearchTvShow(model.inputText))
      )
      is TvSearchSuccess -> Next.next(
        model.copy(
          searchResults = event.results,
          searchEnabled = true
        ), setOf(HideKeyboard)
      )
      is TvSearchFailure -> Next.next(
        model.copy(
          error = true,
          searchEnabled = true
        ), setOf(
          HideKeyboard, DisplayErrorMessage(
            event.ex
          )
        )
      )
      is ItemClicked -> Next.next(
        model, setOf(NavigateToDetail(event.id))
      )
      is DetailViewResumed -> Next.next(
        model.copy(loading = true), setOf(GetTvShowDetail(event.id))
      )
      is TvShowDetailSuccess -> Next.next(
        model.copy(loading = false, detailResult = event.results)
      )
      is TvShowDetailFailure -> Next.next(
        model.copy(loading = false), setOf(DisplayErrorMessage(event.ex))
      )
      else -> Next.noChange()
    }
}


