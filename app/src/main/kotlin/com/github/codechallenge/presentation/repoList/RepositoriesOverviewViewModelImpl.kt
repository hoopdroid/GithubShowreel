package com.github.codechallenge.presentation.repoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.codechallenge.domain.userRepos.UserReposInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
internal class RepositoriesOverviewViewModelImpl @Inject constructor(private val interactor: UserReposInteractor) :
    RepositoriesOverviewViewModel, ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val repositoriesLiveData = MutableLiveData<RepositoriesOverviewState>()

    init {
        fetchRepositories()
    }

    override fun fetchRepositories() {
        repositoriesLiveData.postValue(RepositoriesOverviewState.Loading)

        compositeDisposable.add(
            interactor.loadUserRepos().observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    repositoriesLiveData.postValue(RepositoriesOverviewState.RepositoriesLoaded(it))
                }, {
                    repositoriesLiveData.postValue(RepositoriesOverviewState.Error)
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    override fun observeRepositories(): LiveData<RepositoriesOverviewState> {
        return repositoriesLiveData
    }
}
