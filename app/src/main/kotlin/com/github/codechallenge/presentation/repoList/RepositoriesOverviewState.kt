package com.github.codechallenge.presentation.repoList

internal sealed class RepositoriesOverviewState {
    object Loading : RepositoriesOverviewState()
    object Error : RepositoriesOverviewState()
    data class RepositoriesLoaded(val repositories: List<RepositoryVO>) : RepositoriesOverviewState()
}
