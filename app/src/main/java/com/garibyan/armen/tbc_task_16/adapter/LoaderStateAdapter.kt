package com.garibyan.armen.tbc_task_16.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.garibyan.armen.tbc_task_16.databinding.ErrorStateBinding

class LoaderStateAdapter constructor(private val retry: () -> Unit) :
    LoadStateAdapter<LoaderStateAdapter.LoaderStateViewHolder>() {

    class LoaderStateViewHolder(private val binding: ErrorStateBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryBtn.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                retryBtn.isVisible = loadState !is LoadState.Loading
                errorTxt.isVisible = loadState !is LoadState.Loading
            }
        }

    }

    override fun onBindViewHolder(holder: LoaderStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = LoaderStateViewHolder(
        ErrorStateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), retry
    )
}