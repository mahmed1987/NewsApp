package com.naggaro.dtos.serverObjects

import com.google.gson.annotations.SerializedName
import com.naggaro.dtos.news.NewsView
import com.naggaro.dtos.news.PictureView

data class NewsHolder(
    val status: String,
    val copyright: String,
    val numResults: Int,
    val results: List<News>
)
{
    fun toView()=results.map { it.toView() }
}

data class News(
    val uri: String,
    val url: String,
    val id: Long,
    val asset_id: Long,
    val source: String,
    val published_date: String,
    val updated: String,
    val section: String,
    val subsection: String,
    val nytdsection: String,
    val adx_keywords: String,
    val column: String?,
    val byline: String,
    val type: String,
    val title: String,
    val abstract: String,
    val media: List<Media>,
    )
{
    fun toView() = NewsView(id=id,title=title,abstract=abstract,authors = byline,date = published_date,pictures = media.map { it.toView() }.flatten())
}

data class Media(
    val type: String,
    val subtype: String,
    val caption: String,
    val copyright: String,
    val approved_for_syndication: Int,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetaData>,
)
{
    fun toView()=mediaMetadata.map { it.toView() }
}

data class MediaMetaData(val url: String, val format: String, val height: Int, val width: Int)
{
    fun toView()=PictureView(url, format,height,width)
}