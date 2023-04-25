package com.github.codechallenge.data.model

import com.squareup.moshi.Json
import org.joda.time.DateTime

internal data class CommitsResponseItem(
    @Json(name = "author")
    val author: Author?,
    @Json(name = "commit")
    val commit: Commit,
) {
    data class Author(
        @Json(name = "id")
        val id: Int,
        @Json(name = "login")
        val login: String,
    )

    data class Commit(
        @Json(name = "author")
        val author: AuthorData,
        @Json(name = "message")
        val message: String,
    )

    data class AuthorData(
        @Json(name = "date")
        val date: DateTime,
        @Json(name = "email")
        val email: String,
        @Json(name = "name")
        val name: String
    )
}

