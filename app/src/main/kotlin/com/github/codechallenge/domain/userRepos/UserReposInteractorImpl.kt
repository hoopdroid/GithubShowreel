package com.github.codechallenge.domain.userRepos

import com.github.codechallenge.data.model.repos.UserReposDataSource
import com.github.codechallenge.data.network.di.AndroidGithubUser
import com.github.codechallenge.domain.di.IOScheduler
import com.github.codechallenge.domain.userRepos.mapper.UserReposMapper
import com.github.codechallenge.presentation.repoList.RepositoryVO
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

internal class UserReposInteractorImpl @Inject constructor(
    private val dataSource: UserReposDataSource,
    private val mapper: UserReposMapper,
    @AndroidGithubUser
    private val username: String,
    @IOScheduler
    private val ioScheduler: Scheduler
) : UserReposInteractor {

    override fun loadUserRepos(): Single<List<RepositoryVO>> {
        return dataSource.getUserRepos(username)
            .map {
                it.map {
                    mapper.toVO(it)
                }
            }
            .subscribeOn(ioScheduler)
    }
}