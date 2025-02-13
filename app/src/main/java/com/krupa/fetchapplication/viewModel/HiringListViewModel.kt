package com.krupa.fetchapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krupa.fetchapplication.network.repository.FetchHiringRepository
import com.krupa.fetchapplication.network.response.HiringResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HiringListViewModel @Inject constructor(
    private val fetchHiringRepository: FetchHiringRepository
) : ViewModel() {

    private val _dataList = MutableStateFlow<List<HiringResponse>>(emptyList())
    val dataList: StateFlow<List<HiringResponse>> = _dataList

    private val _errorState = MutableStateFlow<String?>(null) // Error state to hold error messages
    val errorState: StateFlow<String?> = _errorState

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = fetchHiringRepository.fetchHiring().sortedWith(compareBy({ it.listId }, { it.name }))
                //update data state
                withContext(Dispatchers.Main){
                    _dataList.value = result
                    _errorState.value = null
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main){
                    _errorState.value = "Failed to load data: ${e.message}"
                }
            }
        }
    }
}