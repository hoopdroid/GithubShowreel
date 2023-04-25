package com.github.codechallenge.domain.userRepos.mapper

import com.github.codechallenge.base.utils.DateFormatter
import com.github.codechallenge.data.model.RepositoryResponseItem
import com.github.codechallenge.presentation.repoList.RepositoryVO
import javax.inject.Inject

internal class UserReposMapper @Inject constructor(private val dateFormatter: DateFormatter) {
    fun toVO(item: RepositoryResponseItem): RepositoryVO =
        RepositoryVO(
            id = item.id,
            title = "${item.name}",
            openIssues = item.openIssuesCount ?: 0,
            lastUpdate = dateFormatter.format(item.createdAt),
        )
}