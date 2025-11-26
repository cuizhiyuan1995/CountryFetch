package com.classic.countrynamefetch

import com.classic.countrynamefetch.apiFetch.Country
import com.classic.countrynamefetch.apiFetch.CountryRepository
import com.classic.countrynamefetch.apiFetch.Currency
import com.classic.countrynamefetch.apiFetch.Language
import com.classic.countrynamefetch.viewModel.MainViewModel
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import org.junit.Test

import org.junit.Assert.*

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class CountryViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: CountryRepository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = MainViewModel(repository)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadCountries should update state with data`() = runTest {
        // given
        val fakeList: List<Country> = listOf(
            Country("USA",
                "US",
                "Washington",
                "NA",
                currency = Currency("",""," "),
                language = Language("","") ,
                flag="")
        )
        coEvery { repository.getAllCountries() } returns fakeList

        // when
        viewModel.searchCountry()
        advanceUntilIdle()

        // then
        Truth.assertThat(viewModel.fetchResults)
            .isEqualTo(fakeList)
    }

}