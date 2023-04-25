package com.github.codechallenge.presentation.repoList

import androidx.lifecycle.LiveData

internal interface RepositoriesOverviewViewModel {

    fun fetchRepositories()
    fun observeRepositories(): LiveData<RepositoriesOverviewState>
}