package com.naggaro.newsapp.news.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.naggaro.common.newsapp.base.BaseViewModel
import com.naggaro.dtos.news.NewsView
import com.naggaro.newsapp.business.news.usecases.GetMostViewedNews

class NewsViewModel(application: Application, private val getMostViewedNews: GetMostViewedNews) :
    BaseViewModel(application) {

    private val _mostViewedNews = MutableLiveData<List<NewsView>>()

    val mostViewedNews: LiveData<List<NewsView>>
        get() = _mostViewedNews

    fun fetchMostViewedNews(section: String, period: Int) {
        operationStatus.value = Operation.STARTED
        getMostViewedNews(GetMostViewedNews.Params(section, period)) {
            it.either(::handleFailure)
            {
                _mostViewedNews.value = it
                operationStatus.value = Operation.COMPLETED
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }


}