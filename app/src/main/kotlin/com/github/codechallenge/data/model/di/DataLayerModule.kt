package com.github.codechallenge.data.model.di

import com.github.codechallenge.data.model.commits.CommitsDataSource
import com.github.codechallenge.data.model.commits.CommitsDataSourceImpl
import com.github.codechallenge.data.model.repos.UserReposDataSourceImpl
import com.github.codechallenge.data.model.repos.UserReposDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataLayerModule {
    companion object {
        @Provides
        @Singleton
        fun provideCalendar(): Calendar {
            return Calendar.getInstance()
        }
    }

    @Binds
    @Singleton
    fun bindUserReposDataSource(impl: UserReposDataSourceImpl): UserReposDataSource

    @Binds
    @Singleton
    fun bindCommitsHistoryDataSource(impl: CommitsDataSourceImpl): CommitsDataSource
}

