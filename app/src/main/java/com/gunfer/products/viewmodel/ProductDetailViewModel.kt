package com.gunfer.products.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gunfer.products.model.Product
import com.gunfer.products.repository.source.ProductApiRepository
import com.gunfer.products.repository.source.ProductLocalRepository
import com.gunfer.products.service.ProductAPIService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val productLocalRepository: ProductLocalRepository,
                                                 private val productApiRepository: ProductApiRepository,
                                                 application: Application):BaseViewModel(application) {
    val productDetailLiveData = MutableLiveData<Product?>()
    private val productAPIService = ProductAPIService()

    fun getDataFromRoom(productId: String) {
        launch {
            val product: Product
            withContext(Dispatchers.IO) {
                 product = productLocalRepository.getProduct(productId)
            }
            productDetailLiveData.value = product
        }
    }

    fun getDataFromAPI(productId: String) {
        launch {
            withContext(Dispatchers.IO){
                try {
                    var response = productApiRepository.getProduct(productId)
                    response.body()?.let { updateProduct(it) }
                } catch (e: java.lang.Exception) {
                    Log.e("ProductDetailViewModel", e.message.toString())
                }
                finally { getDataFromRoom(productId) }
            }
        }
    }

    fun liveDataClear () {
        productDetailLiveData.value  = null
    }

    private fun updateProduct(product: Product) {
        productLocalRepository.insertProduct(product)
    }

}