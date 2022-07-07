package com.example.offerwalltest.ui.fragments.main

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import androidx.core.net.toUri
import com.example.offerwalltest.base.BaseFragment
import com.example.offerwalltest.data.response.IdsObjData
import com.example.offerwalltest.databinding.FragmentMainBinding
import com.example.offerwalltest.utils.extensions.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::class.java) {

    override val viewModel: MainViewModel by viewModel()

    private var ids: List<IdsObjData> = listOf()
    private var currentItem: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        binding.next.setOnClickListener {
            if (ids.isNotEmpty())
                currentItem++
                if (ids.size > currentItem){
                    viewModel.getItem(ids[currentItem].id)
                } else {
                    currentItem = 0
                    viewModel.getItem(ids[currentItem].id)
                }
        }
        val webSettings: WebSettings = binding.web.settings
        webSettings.javaScriptEnabled = true
    }

    private fun observe() {
        observeLiveData(viewModel.ldIds){
            ids = it!!.data
            ids[0].let{ i -> viewModel.getItem(i.id) }
        }
        observeLiveData(viewModel.ldItem){
            with(binding) {
                when (it?.type) {
                    "text" -> {
                        text.visible()
                        web.gone()
                        image.gone()
                        text.text = it.message
                    }
                    "webview" -> {
                        text.gone()
                        web.visible()
                        image.gone()
                        it.url?.let { url -> web.loadUrl(url) }
                    }
                    "image" -> {
                        /*text.gone()
                        web.gone()
                        image.visible()
                        it.url?.let {url ->*/
                            //image.loadVector(requireActivity(), url)
                            /*image.setImageURI(url.toUri())
                            image.loadImage(url)*/
                        //}
                        text.gone()
                        web.visible()
                        image.gone()
                        it.url?.let { url -> web.loadUrl(url) }
                    }
                    "game" -> {
                        text.gone()
                        web.gone()
                        image.gone()
                    }
                    else -> {
                        text.gone()
                        web.gone()
                        image.gone()
                    }
                }
            }
        }
    }
}