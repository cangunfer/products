package com.gunfer.products.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.gunfer.products.R
import com.gunfer.products.databinding.FragmentProductDetailBinding
import com.gunfer.products.util.downloadFromUrl
import com.gunfer.products.util.placeholderProgress
import com.gunfer.products.viewmodel.ProductDetailViewModel
import com.gunfer.products.viewmodel.ProductsViewModel
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.item_product.*


class ProductDetailFragment : Fragment() {
    private var productId = ""
    private lateinit var viewModel: ProductDetailViewModel
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            productId = ProductDetailFragmentArgs.fromBundle(it).productId
        }
        viewModel =
            ViewModelProvider(activity as FragmentActivity).get(ProductDetailViewModel::class.java)
        viewModel.getDataFromRoom(productId)
        viewModel.getDataFromAPI(productId)
        observeLiveData()
    }

    fun observeLiveData() {
        viewModel.productDetailLiveData.observe(viewLifecycleOwner, Observer { productDetail ->
            productDetail?.let {
                binding.productDetail = productDetail
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onDetach() {
        super.onDetach()
        viewModel.liveDataClear()
    }
}