package com.gunfer.products.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gunfer.products.model.Product
import com.gunfer.products.repository.source.ProductApiRepository
import com.gunfer.products.repository.source.ProductRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productLocalRepository: ProductRepositoryInterface,
                                            private val productApiRepository: ProductApiRepository,
                                            application: Application) : BaseViewModel(application) {
    val products = MutableLiveData<List<Product>>()
    val productsError = MutableLiveData<Boolean>()
    val productsLoading = MutableLiveData<Boolean>()

    init {
        refreshData()
    }

    fun refreshData() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        launch {
            withContext(Dispatchers.IO) {
                try {
                    var response = productApiRepository.getAllProducts()
                    response.body()?.let { storeInSQLite(it.products) }
                } catch (e: Exception) {
                    Log.e("ProductsViewModel", e.message.toString())
                } finally { getDataFromSQLite() }
            }
        }
    }

    private fun getDataFromSQLite() {
        launch {
            var data: MutableList<Product>
            withContext(Dispatchers.IO) {
                data = productLocalRepository.getAllProducts().toMutableList()
            }
            data.let {
                if (data.size > 0) showProducts(data)
                else (productsError.postValue(true))
            }
        }
    }

    fun showProducts(productList: List<Product>) {
        products.value = productList
        productsError.value = false
        productsLoading.value = false
    }

    private fun storeInSQLite(list: List<Product>) {
        productLocalRepository.insertAll(*list.toTypedArray())
    }

}