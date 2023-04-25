package com.github.codechallenge.data.model

import com.squareup.moshi.Json
import org.joda.time.DateTime

internal data class RepositoryResponseItem(
    @Json(name = "id")
    val id: Int,
    @Json(name = "created_at")
    val createdAt: DateTime?,
    @Json(name = "open_issues_count")
    val openIssuesCount: Int?,
    @Json(name = "name")
    val name: String?,
)
