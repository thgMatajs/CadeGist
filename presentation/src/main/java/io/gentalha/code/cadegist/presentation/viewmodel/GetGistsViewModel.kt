package io.gentalha.code.cadegist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava2.cachedIn
import androidx.paging.rxjava2.flowable
import io.gentalha.code.cadegist.presentation.pagination.GistDataSource

class GetGistsViewModel(
    private val dataSource: GistDataSource
) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 6)) {
        dataSource
    }.flowable.cachedIn(viewModelScope)
}