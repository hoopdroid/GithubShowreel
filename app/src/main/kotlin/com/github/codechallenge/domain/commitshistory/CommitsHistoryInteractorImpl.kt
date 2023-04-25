package com.github.codechallenge.domain.commitshistory

import com.github.codechallenge.data.model.CommitsResponseItem
import com.github.codechallenge.base.utils.DateFormatter
import com.github.codechallenge.data.model.commits.CommitsDataSource
import com.github.codechallenge.data.network.di.AndroidGithubUser
import com.github.codechallenge.domain.MonthData
import com.github.codechallenge.domain.di.IOScheduler
import com.github.codechallenge.presentation.commits.CommitsScreenState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class CommitsHistoryInteractorImpl @Inject constructor(
    private val commitsDataSource: CommitsDataSource,
    private val dateFormatter: DateFormatter,
    @AndroidGithubUser
    private val username: String,
    @IOScheduler
    private val ioScheduler: Scheduler,
) : CommitsHistoryInteractor {

    override fun commitsUpdatesObservable(
        timerInterval: Long,
        repositoryName: String
    ): Observable<CommitsScreenState.CommitsLoaded> {
        return commitsDataSource.getCommitsObservable(username, repositoryName)
            .flatMap { timerObservable(timerInterval, it) }
            .subscribeOn(ioScheduler)
    }

    private fun timerObservable(
        timerInterval: Long,
        allCommits: List<CommitsResponseItem>
    ): Observable<CommitsScreenState.CommitsLoaded> {
        val result = mapCommitByYears(allCommits)
        var currentPosition = 0
        var currentResult = result[currentPosition]
        val authors = allCommits.groupBy { it.author?.login }

        return Observable.interval(timerInterval, TimeUnit.MILLISECONDS)
            .map {
                val name =
                    "${dateFormatter.getMonthName(currentResult.monthOfYear)} ${currentResult.year}"
                val commits = currentResult.commits
                val yearProgress =
                    (currentResult.commits.toFloat() / allCommits.size.toFloat() * 100).toInt()

                CommitsScreenState.CommitsLoaded(
                    authors.map { it.key ?: "" }.toList(),
                    allCommits.size,
                    CommitsScreenState.CommitsLoaded.CommitsDisplayData(
                        name,
                        commits,
                        yearProgress
                    )
                )

            }.doAfterNext {
                currentPosition++
                currentResult = result[currentPosition]
                if (currentPosition == result.size - 1) {
                    currentPosition = 0
                    currentResult = result[currentPosition]
                }
            }
    }

    private fun mapCommitByYears(allCommits: List<CommitsResponseItem>): MutableList<MonthData> {
        val result = mutableListOf<MonthData>()
        val byYears = allCommits
            .asSequence()
            .sortedBy { it.commit.author.date }
            .groupBy { it.commit.author.date.year }

        byYears.asSequence().forEach { year ->
            year.value
                .groupBy { it.commit.author.date.monthOfYear }
                .asSequence()
                .forEach {
                    result.add(MonthData(year.key, it.key, it.value.size))
                }
        }
        return result
    }
}