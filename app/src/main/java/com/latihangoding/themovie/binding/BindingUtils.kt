package com.latihangoding.themovie.binding

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihangoding.themovie.BuildConfig

@BindingAdapter("image_url")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    val circularProgressDrawable = CircularProgressDrawable(imgView.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    imgUrl?.let {
        Glide.with(imgView.context)
            .load("${BuildConfig.IMAGE_URL}$imgUrl")
            .apply(RequestOptions())
            .placeholder(circularProgressDrawable)
            .into(imgView)
    }
}

@BindingAdapter("progress_bar_vote")
fun bindProgressBarVote(progressBar: ProgressBar, vote: Float) {
    val tmp = (vote * 10).toInt()
    progressBar.progress = tmp
}

@BindingAdapter("text_vote")
fun bindTextViewVote(textView: TextView, vote: Float) {
    val tmp = (vote * 10).toInt()
    textView.text = "$tmp%"
}