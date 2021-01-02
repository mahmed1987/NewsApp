package com.naggaro.newsapp.news.ui

import com.naggaro.dtos.news.PictureView
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import org.junit.After


class PictureUtilTest {

    private val pictureViews = mutableListOf<PictureView>()

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {
        pictureViews.clear()
    }


    @Test
    fun `should return the largest image`() {
        pictureViews.add(PictureView("1", "__", 75, 75))
        pictureViews.add(PictureView("2", "__", 140, 210))
        pictureViews.add(PictureView("3", "__", 293, 440))
        val largestPicture = PictureUtil.findLargestImage(pictureViews)
        assertThat(largestPicture.url).isEqualTo("3")

    }
    @Test
    fun `should return empty if input is empty`() {
        val largestPicture = PictureUtil.findLargestImage(pictureViews)
        assertThat(largestPicture.url).isEqualTo("")
    }

}