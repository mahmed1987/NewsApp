package com.naggaro.newsapp.news.ui

import com.naggaro.dtos.news.PictureView

object PictureUtil {
    fun findLargestImage(pictureViews: List<PictureView>): PictureView {
        return pictureViews.maxByOrNull { it.height + it.width } ?:PictureView.empty()
    }
}