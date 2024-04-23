package com.gunfer.products.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gunfer.products.R
import com.gunfer.products.databinding.ItemProductBinding
import com.gunfer.products.model.Product
import com.gunfer.products.view.ProductsFragmentDirections
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(val productList: ArrayList<Product>):RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(),ProductClickListener {

    class ProductViewHolder(var view: ItemProductBinding): RecyclerView.ViewHolder(view.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemProductBinding>(inflater,R.layout.item_product,parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.view.product = productList[position]
        holder.view.listener = this
    }

    fun updateProductList(newProductList: List<Product>)  {
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onProductClicked(v: View) {
        val productId = v.productId.text.toString()
        val action = ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment(productId)
        Navigation.findNavController(v).navigate(action)
    }
}
