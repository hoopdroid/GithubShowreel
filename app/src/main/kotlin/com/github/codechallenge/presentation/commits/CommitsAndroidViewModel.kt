package com.github.codechallenge.presentation.commits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.codechallenge.domain.commitshistory.CommitsHistoryInteractor
import com.github.codechallenge.domain.di.MainThreadScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
internal class CommitViewModelImpl @Inject constructor(
    private val interactor: CommitsHistoryInteractor,
    @MainThreadScheduler
    private val scheduler: Scheduler
) :
    CommitsScreenViewModel, ViewModel() {
    companion object {
        private const val TIMER_INTERVAL = 1500L
    }

    private val compositeDisposable = CompositeDisposable()
    private val commitsLiveData = MutableLiveData<CommitsScreenState>()

    override fun subscribeToCommitsUpdates(id: String) {
        commitsLiveData.postValue(CommitsScreenState.Loading)

        compositeDisposable.add(
            interactor.commitsUpdatesObservable(TIMER_INTERVAL, id)
                .observeOn(scheduler)
                .subscribe({
                    commitsLiveData.postValue(it)
                }, {
                    commitsLiveData.postValue(CommitsScreenState.Error())
                })
        )
    }

    override fun observeCommitsUpdates(): LiveData<CommitsScreenState> {
        return commitsLiveData
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
