package com.gunfer.products.repository.dao

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.gunfer.products.data.dao.ProductDAO
import com.gunfer.products.model.Product
import com.gunfer.products.data.database.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class ProductDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao : ProductDAO
    private lateinit var database: AppDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), AppDatabase::class.java)
            .allowMainThreadQueries().build()

        dao = database.productDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertProductTesting() = runBlockingTest {
        val exampleProduct = Product("999","fruit juice", 999,"amazing", "test.com")
        dao.insertProduct(exampleProduct)
        assertThat(dao.getProduct("999")).isNotNull()
    }

    @Test
    fun deleteProductTesting() = runBlockingTest {
        val exampleProduct = Product("999","fruit juice", 999,"amazing", "test.com")
        dao.insertProduct(exampleProduct)
        dao.deleteAllProducts()
        assertThat(dao.getProduct("999")).isNull()
    }
}