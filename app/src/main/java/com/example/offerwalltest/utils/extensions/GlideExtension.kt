package com.example.offerwalltest.utils.extensions

import android.app.Activity
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.StreamEncoder
import com.bumptech.glide.request.RequestOptions
import com.example.offerwalltest.R
import java.io.InputStream


fun ImageView.loadImage(src: String) {
    Glide.with(context)
        .load(src)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(RequestOptions().error(R.drawable.bg_btn))
        .into(this)
}

/*fun ImageView.loadVector(activity: Activity, src: String){

    val requestBuilder = Glide.with(activity)
        .using(Glide.buildStreamModelLoader(Uri::class.java, activity), InputStream::class.java)
        .from(Uri::class.java)
        .`as`(SVG::class.java)
        .transcode(SvgDrawableTranscoder(), PictureDrawable::class.java)
        .sourceEncoder(StreamEncoder())
        .cacheDecoder(FileToStreamDecoder<SVG>(SvgDecoder()))
        .decoder(SvgDecoder())
        .placeholder(R.drawable.ic_facebook)
        .error(R.drawable.ic_web)
        .animate(android.R.anim.fade_in)
        .listener(SvgSoftwareLayerSetter<Uri>())

    val uri: Uri =
        Uri.parse("https://de.wikipedia.org/wiki/Scalable_Vector_Graphics#/media/File:SVG_logo.svg")
    requestBuilder
        .diskCacheStrategy(DiskCacheStrategy.SOURCE) // SVG cannot be serialized so it's not worth to cache it
        .load(uri)
        .into(mImageView)
}*/