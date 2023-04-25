package com.github.codechallenge.presentation.repoList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.github.codechallenge.databinding.RepositoryListItemBinding
import com.github.codechallenge.presentation.repoList.RepositoryVO


internal class RepositoriesAdapter(private val itemClickAction: (result: String?) -> Unit) :
    ListAdapter<RepositoryVO, RepositoriesViewHolder>(
        object : DiffUtil.ItemCallback<RepositoryVO>() {
            override fun areItemsTheSame(oldItem: RepositoryVO, newItem: RepositoryVO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepositoryVO, newItem: RepositoryVO): Boolean {
                return oldItem == newItem
            }

        }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        return RepositoriesViewHolder(
            RepositoryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), itemClickAction
        )
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
