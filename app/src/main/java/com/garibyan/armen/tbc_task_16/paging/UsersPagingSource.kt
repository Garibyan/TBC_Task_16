package com.garibyan.armen.tbc_task_16.paging

import android.util.Log.d
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.garibyan.armen.tbc_task_16.network.ApiService
import com.garibyan.armen.tbc_task_16.network.ResponseModel

class UsersPagingSource(private val apiService: ApiService): PagingSource<Int, ResponseModel.Person>() {

    override fun getRefreshKey(state: PagingState<Int, ResponseModel.Person>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseModel.Person> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getUsers(nextPageNumber)

            LoadResult.Page(
                data = response.data,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1 ,
                nextKey = if (response.data.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}