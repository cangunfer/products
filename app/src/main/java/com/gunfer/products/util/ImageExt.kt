package com.gunfer.products.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gunfer.products.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ImageView.downloadFromUrl(url: String, progressDrawable: CircularProgressDrawable) {
    val view = this
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    CoroutineScope(Dispatchers.Main.immediate).launch {
        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(url)
            .into(view)
    }
}

fun placeholderProgress(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context)
        .apply {
            strokeWidth = 8f
            centerRadius = 40f
            start()
        }
}

@BindingAdapter("download_url")
fun downloadImage(view: ImageView, url: String?) {
    url?.let { view.downloadFromUrl(it, placeholderProgress(view.context)) }
}