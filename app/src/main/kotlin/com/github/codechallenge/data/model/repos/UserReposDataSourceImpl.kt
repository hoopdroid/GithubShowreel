package com.github.codechallenge.data.model.repos

import com.github.codechallenge.data.model.RepositoryResponseItem
import com.github.codechallenge.data.network.Api
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

internal class UserReposDataSourceImpl @Inject constructor(private val api: Api) : UserReposDataSource {

    override fun getUserRepos(username: String): Single<List<RepositoryResponseItem>> {
        return api.getRepositories(username)
    }
}