package com.naggaro.newsapp.news.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.naggaro.common.newsapp.base.BaseViewModel
import com.naggaro.common.newsapp.base.CoroutineContextProvider
import com.naggaro.dtos.news.NewsView
import com.naggaro.newsapp.business.news.usecases.GetMostViewedNews
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewsViewModel(application: Application, private val getMostViewedNews: GetMostViewedNews) :
    BaseViewModel(application) {

    private val _mostViewedNews = MutableLiveData<List<NewsView>>()

    val mostViewedNews: LiveData<List<NewsView>>
        get() = _mostViewedNews

    fun fetchMostViewedNews(params: GetMostViewedNews.Params) {
        operationStatus.value = Operation.STARTED
        getMostViewedNews(params) {
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