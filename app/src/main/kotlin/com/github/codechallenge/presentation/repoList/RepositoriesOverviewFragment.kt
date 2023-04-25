package com.github.codechallenge.presentation.repoList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.codechallenge.base.utils.gone
import com.github.codechallenge.base.utils.show
import com.github.codechallenge.databinding.RepositoriesOverviewBinding
import com.github.codechallenge.presentation.repoList.adapter.RepositoriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoriesOverviewFragment : Fragment() {
    private lateinit var binding: RepositoriesOverviewBinding
    private val viewModel: RepositoriesOverviewViewModel by viewModels<RepositoriesOverviewViewModelImpl>()
    private val repositoriesAdapter: RepositoriesAdapter = RepositoriesAdapter {
        findNavController().navigate(
            RepositoriesOverviewFragmentDirections.actionRepositoryListToCommitScreenFragment()
                .apply {
                    id = it ?: ""
                }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RepositoriesOverviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        observeScreenState()
    }

    private fun observeScreenState() {
        viewModel.observeRepositories().observe(viewLifecycleOwner) { state ->

            when (state) {
                is RepositoriesOverviewState.Error -> {
                    binding.repositoryList.gone()
                    binding.swipeRefreshContainer.isRefreshing = false
                }
                is RepositoriesOverviewState.RepositoriesLoaded -> {
                    binding.swipeRefreshContainer.isRefreshing = false
                    binding.repositoryList.show()
                    repositoriesAdapter.submitList(state.repositories)
                }
                is RepositoriesOverviewState.Loading -> {
                    binding.repositoryList.gone()
                    binding.swipeRefreshContainer.isRefreshing = true
                }
            }
        }
    }

    private fun initUI() {
        binding.repositoryList.apply {
            adapter = repositoriesAdapter
        }
        binding.swipeRefreshContainer.setOnRefreshListener { viewModel.fetchRepositories() }
    }
}