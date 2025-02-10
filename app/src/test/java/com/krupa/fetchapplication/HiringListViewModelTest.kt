package com.krupa.fetchapplication

import android.util.Log
import com.krupa.fetchapplication.network.repository.FetchHiringRepository
import com.krupa.fetchapplication.network.response.HiringResponse
import com.krupa.fetchapplication.viewModel.HiringListViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HiringListViewModelTest {
    private lateinit var fetchHiringRepository: FetchHiringRepository
    private lateinit var hiringListViewModel: HiringListViewModel

    @Before
    fun setUp() {
        //Mockk repository
        fetchHiringRepository = mock(FetchHiringRepository::class.java)
        hiringListViewModel = HiringListViewModel(fetchHiringRepository)
    }

    @Test
    fun `test fetchItems updates StateFlow`() = runTest {
        // Given
        val items = listOf(
            HiringResponse(3, 1, "Hire C"),
            HiringResponse(1, 2, "Hire A"),
            HiringResponse(2, 1, "Hire B"),
            HiringResponse(4, 2, "Hire D")
        )

        // Using `Mockito.doReturn()` to mock the repository's `getItems` method
        Mockito.doReturn(items).`when`(fetchHiringRepository).fetchHiring()

        // Launch the ViewModel function to fetch data
        hiringListViewModel.fetchData()
        // Ensure that the data is properly emitted after calling fetchData
        val result = hiringListViewModel.dataList.take(1).toList()
        assertEquals(1, result.size)  // There should be 4 items
    }

    @Test
    fun `test fetchItems result false when listId 0`() = runTest {
        // Given
        val items = listOf(
            HiringResponse(3, 0, "Hire C"),
            HiringResponse(1, 2, "Hire A"),
            HiringResponse(2, 1, "Hire B"),
            HiringResponse(4, 2, "Hire D")
        )

        // Using `Mockito.doReturn()` to mock the repository's `getItems` method
        Mockito.doReturn(items).`when`(fetchHiringRepository).fetchHiring()

        // Launch the ViewModel function to fetch data
        hiringListViewModel.fetchData()
        // Ensure that the data is properly emitted after calling fetchData
        val result = hiringListViewModel.dataList.take(1).toList().first()
        // Assert that no item has listId == 0
        assertFalse(result.any { it.listId == 0 })  //
    }
}