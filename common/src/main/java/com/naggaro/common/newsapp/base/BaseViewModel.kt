
package com.naggaro.common.newsapp.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.naggaro.common.error.Failure
import com.naggaro.common.newsapp.SingleLiveEvent

abstract class BaseViewModel(androidApplication: Application) : AndroidViewModel(androidApplication)
{
    var failure: SingleLiveEvent<Failure> = SingleLiveEvent() // the failure should just emit once. We don't want failures to emit when an observer resubscribes to this property.
    var operationStatus: SingleLiveEvent<Operation> = SingleLiveEvent()

    protected fun handleFailure(failure:Failure)
    {
        this.failure.value=failure
    }

    enum class Operation {
        STARTED,
        COMPLETED

    }

}