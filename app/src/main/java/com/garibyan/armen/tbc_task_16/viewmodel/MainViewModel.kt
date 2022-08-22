package com.garibyan.armen.tbc_task_16.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.garibyan.armen.tbc_task_16.network.ApiService
import com.garibyan.armen.tbc_task_16.paging.UsersPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val apiService: ApiService
) : ViewModel() {
    val listData = Pager(PagingConfig(pageSize = 1)) {
        UsersPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}


