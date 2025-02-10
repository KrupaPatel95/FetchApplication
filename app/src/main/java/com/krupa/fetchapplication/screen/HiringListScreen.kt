package com.krupa.fetchapplication.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.krupa.fetchapplication.network.response.HiringResponse
import com.krupa.fetchapplication.viewModel.HiringListViewModel

@Composable
fun HiringListScreen(viewModel: HiringListViewModel = hiltViewModel()) {
    val list by viewModel.dataList.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            val groupedItems = list.groupBy { it.listId }
            groupedItems.forEach { (listId, itemsList) ->
                item {
                    Text(
                        text = "ListId: $listId",
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                item {
                    ItemRow(itemList = itemsList)
                }
            }
        }
    }

}

@Composable
fun ItemRow(itemList: List<HiringResponse>) {
    val names = itemList.joinToString(", ") { it.name.orEmpty() }
    Text(
        text = names,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}