package com.naggaro.common.newsapp.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.widget.ImageView
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.use
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.naggaro.newsapp.common.R


//endregion
@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(context.applicationContext).load(url).placeholder(R.drawable.ic_gallery).into(this)
}

