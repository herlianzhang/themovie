package com.latihangoding.themovie.binding

import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.latihangoding.themovie.BuildConfig
import com.latihangoding.themovie.R

@BindingAdapter("image_url")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    val circularProgressDrawable = CircularProgressDrawable(imgView.context)
    circularProgressDrawable.setColorSchemeColors(R.attr.colorPrimary)
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
    textView.text = textView.context.getString(R.string.progress, tmp)
}

fun ImageView.imageUrl(imgUrl: String?, loadingColor: Int = R.attr.colorPrimary) {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.setColorSchemeColors(loadingColor)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    imgUrl?.let {
        Glide.with(context)
            .load("${BuildConfig.IMAGE_URL}$imgUrl")
            .apply(RequestOptions())
            .placeholder(circularProgressDrawable)
            .into(this)
    }
}