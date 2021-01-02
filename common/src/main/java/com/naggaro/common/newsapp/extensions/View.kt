/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.naggaro.common.newsapp.extensions

import android.content.Context
import android.os.Environment
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions



fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}


fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

//ahmed
fun <T : ViewDataBinding> ViewGroup.dataBind(@LayoutRes layoutRes: Int) =
    DataBindingUtil.inflate<T>(LayoutInflater.from(context), layoutRes, this, false)!!

fun <T : ViewDataBinding> ViewGroup.dataBind(@LayoutRes layoutRes: Int, viewType: Int) =
    DataBindingUtil.inflate<T>(LayoutInflater.from(context), layoutRes, this, false)!!

fun ImageView.loadImageFromUrl(url: String) {
    Glide
        .with(this.context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}




