package com.github.codechallenge.data.model.repos

import com.github.codechallenge.data.model.RepositoryResponseItem
import io.reactivex.rxjava3.core.Single

internal interface UserReposDataSource {

    fun getUserRepos(username: String): Single<List<RepositoryResponseItem>>
}