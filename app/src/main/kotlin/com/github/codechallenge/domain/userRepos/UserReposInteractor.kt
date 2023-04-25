package com.github.codechallenge.domain.userRepos

import com.github.codechallenge.presentation.repoList.RepositoryVO
import io.reactivex.rxjava3.core.Single

internal interface UserReposInteractor {
    fun loadUserRepos(): Single<List<RepositoryVO>>
}