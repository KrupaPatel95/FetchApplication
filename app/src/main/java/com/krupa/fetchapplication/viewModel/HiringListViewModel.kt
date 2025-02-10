package com.krupa.fetchapplication.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krupa.fetchapplication.network.repository.FetchHiringRepository
import com.krupa.fetchapplication.network.response.HiringResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CheckResult")
@HiltViewModel
class HiringListViewModel @Inject constructor(
    private val fetchHiringRepository: FetchHiringRepository
) : ViewModel() {

    private val _dataList = MutableStateFlow<List<HiringResponse>>(emptyList())
    val dataList: StateFlow<List<HiringResponse>> = _dataList

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _dataList.value = fetchHiringRepository.fetchHiring().sortedWith(compareBy({ it.listId }, { it.name }))
        }
    }
}