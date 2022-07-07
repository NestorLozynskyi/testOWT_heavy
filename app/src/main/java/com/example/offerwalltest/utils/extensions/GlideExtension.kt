package com.example.offerwalltest.utils.extensions

import android.app.Activity
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.StreamEncoder
import com.bumptech.glide.load.resource.file.FileToStreamDecoder
import com.caverock.androidsvg.SVG
import com.example.offerwalltest.R
import com.example.offerwalltest.utils.drawableSVG.SvgDecoder
import com.example.offerwalltest.utils.drawableSVG.SvgDrawableTranscoder
import com.example.offerwalltest.utils.drawableSVG.SvgSoftwareLayerSetter
import java.io.InputStream


/*fun ImageView.loadImage(src: String) {
    Glide.with(context)
        .load(src)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .apply(RequestOptions().error(R.drawable.bg_btn).also { println("error: ${it.errorId}") })
        .into(this)
}*/

fun ImageView.loadSVG(activity: Activity, src: String) {

    val requestBuilder = Glide.with(activity)
        .using(Glide.buildStreamModelLoader(Uri::class.java, activity), InputStream::class.java)
        .from(Uri::class.java)
        .`as`(SVG::class.java)
        .transcode(SvgDrawableTranscoder(), PictureDrawable::class.java)
        .sourceEncoder(StreamEncoder())
        .cacheDecoder(FileToStreamDecoder(SvgDecoder()))
        .decoder(SvgDecoder())
        .error(R.drawable.bg_btn)
        .animate(android.R.anim.fade_in)
        .listener(SvgSoftwareLayerSetter())

    val uri: Uri = Uri.parse(src)
    requestBuilder
        .diskCacheStrategy(DiskCacheStrategy.SOURCE) // SVG cannot be serialized so it's not worth to cache it
        .load(uri)
        .into(this)
}