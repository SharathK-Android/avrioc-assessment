package com.avrioc.assessment.ui.main.viewmodel

import android.annotation.SuppressLint
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avrioc.assessment.ui.main.utils.DataResult
import com.avrioc.assessment.ui.main.data.ArticlesResponse
import com.avrioc.assessment.ui.main.repository.ArticlesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ArticlesViewModel : ViewModel() {

    val liveData = MutableLiveData<DataResult<ArticlesResponse>>()
    var loadingBarObservable :ObservableBoolean = ObservableBoolean(false)
    private var disposable: Disposable? = null
    private val repository = ArticlesRepository.getInstance()

    init {
        fetchArticlesData()
    }

    @SuppressLint("CheckResult")
    private fun fetchArticlesData() {
        disposable = repository.getResponse()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveData.value = DataResult.Loading(true) }
            .doOnTerminate { liveData.value = DataResult.Loading(false) }
            .subscribe({
               liveData.value = DataResult.Success(it)
            },{
               liveData.value = DataResult.Error(it.message)
            })
    }

    fun handleProgressBar(isLoading: Boolean) {
        loadingBarObservable.set(isLoading)
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}