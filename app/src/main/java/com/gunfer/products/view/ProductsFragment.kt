package com.gunfer.products.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gunfer.products.R
import com.gunfer.products.adapter.ProductAdapter
import com.gunfer.products.data.database.AppDatabase
import com.gunfer.products.viewmodel.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_products.*


class ProductsFragment : Fragment() {

    private lateinit var viewModel: ProductsViewModel
    private val productAdapter = ProductAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(activity as FragmentActivity).get(ProductsViewModel::class.java)
        productList.layoutManager = GridLayoutManager(context, 2)
        productList.adapter = productAdapter

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.products.observe(viewLifecycleOwner, Observer {
            products -> products?.let {
                productList.visibility = View.VISIBLE
                productAdapter.updateProductList(products)
            }
        })

        viewModel.productsError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    productError.visibility = View.VISIBLE
                    productList.visibility = View.GONE
                    productLoading.visibility = View.GONE
                } else {
                    productError.visibility = View.GONE
                }
            }
        })

        viewModel.productsLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    productLoading.visibility = View.VISIBLE
                    productList.visibility = View.GONE
                    productError.visibility = View.GONE
                } else {
                    productLoading.visibility = View.GONE
                }
            }
        })
    }


}