package com.github.codechallenge.presentation.repoList.adapter

import androidx.recyclerview.widget.RecyclerView
import com.github.codechallenge.R
import com.github.codechallenge.databinding.RepositoryListItemBinding
import com.github.codechallenge.presentation.repoList.RepositoryVO

internal class RepositoriesViewHolder(
    private val binding: RepositoryListItemBinding,
    private val itemClickAction: (result: String?) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(repositoryVO: RepositoryVO) {
        binding.apply {
            repositoryNameText.text = repositoryVO.title
            lastUpdateText.text =
                root.context.getString(R.string.last_update_text, repositoryVO.lastUpdate)
            issuesCountText.text =
                root.context.getString(
                    R.string.open_issues_text,
                    repositoryVO.openIssues.toString()
                )
        }
        binding.root.setOnClickListener {
            itemClickAction.invoke(repositoryVO.title)
        }
    }
}