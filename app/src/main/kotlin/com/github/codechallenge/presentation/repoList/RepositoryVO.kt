package com.github.codechallenge.presentation.repoList

internal data class RepositoryVO(
    val id: Int,
    val title: String,
    val openIssues: Int,
    val lastUpdate: String,
)