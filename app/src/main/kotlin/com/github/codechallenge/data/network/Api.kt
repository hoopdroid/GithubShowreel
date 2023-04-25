package com.github.codechallenge.data.network

import com.github.codechallenge.data.model.CommitsResponseItem
import com.github.codechallenge.data.model.RepositoryResponseItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface Api {

    @GET("users/{username}/repos")
    fun getRepositories(@Path("username") username: String): Single<List<RepositoryResponseItem>>

    @GET("repos/{username}/{repositoryName}/commits")
    fun getCommitsHistory(
        @Path("username") username: String,
        @Path("repositoryName") repositoryName: String,
        @Query("per_page") perPage: Int
    ): Single<List<CommitsResponseItem>>
}