package com.github.codechallenge.domain.di

import javax.inject.Qualifier

@Qualifier
annotation class IOScheduler

@Qualifier
internal annotation class MainThreadScheduler