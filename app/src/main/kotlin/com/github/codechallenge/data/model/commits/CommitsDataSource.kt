package com.github.codechallenge.data.model.commits

import com.github.codechallenge.data.model.CommitsResponseItem
import io.reactivex.rxjava3.core.Observable

internal interface CommitsDataSource {

    fun getCommitsObservable(username: String, repositoryName: String): Observable<List<CommitsResponseItem>>
}