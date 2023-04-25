package com.github.codechallenge.domain.commitshistory

import com.github.codechallenge.presentation.commits.CommitsScreenState
import io.reactivex.rxjava3.core.Observable

internal interface CommitsHistoryInteractor {

    fun commitsUpdatesObservable(
        timerInterval: Long,
        repositoryName: String
    ): Observable<CommitsScreenState.CommitsLoaded>
}