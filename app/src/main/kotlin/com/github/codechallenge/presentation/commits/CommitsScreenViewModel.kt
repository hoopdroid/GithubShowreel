package com.github.codechallenge.presentation.commits

import androidx.lifecycle.LiveData

internal interface CommitsScreenViewModel {

    fun subscribeToCommitsUpdates(id: String)
    fun observeCommitsUpdates(): LiveData<CommitsScreenState>
}