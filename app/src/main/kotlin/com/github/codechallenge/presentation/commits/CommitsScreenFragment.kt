package com.github.codechallenge.presentation.commits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.codechallenge.R
import com.github.codechallenge.base.utils.gone
import com.github.codechallenge.base.utils.show
import com.github.codechallenge.databinding.CommitsScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class CommitsFragment : Fragment() {
    private lateinit var binding: CommitsScreenBinding
    private val viewModel: CommitsScreenViewModel by viewModels<CommitViewModelImpl>()
    private val args: CommitsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.subscribeToCommitsUpdates(args.id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CommitsScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeScreenState()
    }

    private fun observeScreenState() {
        viewModel.observeCommitsUpdates().observe(viewLifecycleOwner) { state ->
            when (state) {
                is CommitsScreenState.Error -> {
                    binding.commitsView.gone()
                    binding.repositoryNameText.gone()
                }
                is CommitsScreenState.CommitsLoaded -> {
                    binding.progress.gone()
                    binding.repositoryNameText.show()
                    binding.commitsView.show()
                    binding.repositoryNameText.text =
                        getString(
                            R.string.repository_data_text,
                            args.id,
                            state.overallCommits.toString(),
                            state.authors.toString()
                        )
                    binding.commitsView.bind(
                        state.data.overallPercentage.toFloat(),
                        state.data.month,
                        getString(R.string.commits_text, state.data.commits.toString())
                    )
                }
                is CommitsScreenState.Loading -> {
                    binding.repositoryNameText.gone()
                    binding.commitsView.gone()
                    binding.progress.show()
                }
            }
        }
    }


}

