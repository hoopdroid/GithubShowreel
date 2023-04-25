package com.github.codechallenge.data.model.commits

import com.github.codechallenge.data.model.CommitsResponseItem
import com.github.codechallenge.data.network.Api
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

internal class CommitsDataSourceImpl @Inject constructor(private val api: Api) : CommitsDataSource {

    override fun getCommitsObservable(
        username: String,
        repositoryName: String
    ): Observable<List<CommitsResponseItem>> {
        return api.getCommitsHistory(username, repositoryName, 100)
            .toObservable()
    }
}