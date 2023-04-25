package com.github.codechallenge.domain.di

import com.github.codechallenge.domain.commitshistory.CommitsHistoryInteractor
import com.github.codechallenge.domain.commitshistory.CommitsHistoryInteractorImpl
import com.github.codechallenge.domain.userRepos.UserReposInteractor
import com.github.codechallenge.domain.userRepos.UserReposInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DomainLayerModule {
    companion object {
        @Provides
        @Singleton
        @IOScheduler
        fun provideIOScheduler(): Scheduler {
            return Schedulers.io()
        }

        @Provides
        @Singleton
        @MainThreadScheduler
        fun provideMainScheduler(): Scheduler {
            return AndroidSchedulers.mainThread()
        }
    }

    @Binds
    @Singleton
    fun bindRepositoriesListInteractor(interactorImpl: UserReposInteractorImpl): UserReposInteractor

    @Binds
    @Singleton
    fun bindCommitsHistoryInteractor(interactorImpl: CommitsHistoryInteractorImpl): CommitsHistoryInteractor
}