package com.naggaro.newsapp.news

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Subject(val scope: CoroutineScope) {
    fun foo() {
        scope.launch {
            val name ="Ahmed"
            return@launch
        }
    }
}