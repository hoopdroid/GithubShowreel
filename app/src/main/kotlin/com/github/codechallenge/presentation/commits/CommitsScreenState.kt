package com.github.codechallenge.presentation.commits


internal sealed class CommitsScreenState {
    object Loading : CommitsScreenState()
    data class Error(val message: String? = null) : CommitsScreenState()
    data class CommitsLoaded(
        val authors: List<String>,
        val overallCommits: Int,
        val data: CommitsDisplayData
    ) : CommitsScreenState() {

        data class CommitsDisplayData(
            val month: String,
            val commits: Int,
            val overallPercentage: Int
        )
    }
}