package com.irfan.moviecatalogue.fragment.tv


import com.irfan.moviecatalogue.utils.TvData
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test

class TvViewModelTest {

    private lateinit var tvViewModel: TvViewModel
    private val dummyMovie = TvData.listData

    @Before
    fun setUp() {
        tvViewModel = TvViewModel()
    }

    @Test
    fun `get List of Tv Data, return should not be null`() {
        tvViewModel.setData()
        val tv = tvViewModel.getData()
        assertNotNull(tv)
        assertEquals(dummyMovie, tv)
    }
}