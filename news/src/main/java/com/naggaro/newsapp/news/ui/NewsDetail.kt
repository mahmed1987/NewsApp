package com.naggaro.newsapp.news.ui

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.naggaro.common.newsapp.base.BaseFragment
import com.naggaro.common.newsapp.base.GeneralAdapter
import com.naggaro.common.newsapp.extensions.configureVerticalList
import com.naggaro.common.newsapp.extensions.themeColor
import com.naggaro.dtos.news.NewsView
import com.naggaro.newsapp.news.BR
import com.naggaro.newsapp.news.R
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsDetail : BaseFragment() {
    override val layoutResourceId = R.layout.fragment_news_detail
    override var shouldBindData=true
    private val args:NewsDetailArgs by navArgs()
    override fun interaction() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // Scope the transition to a view in the hierarchy so we know it will be added under
            // the bottom app bar but over the elevation scale of the exiting HomeFragment.
            drawingViewId = R.id.nav_host_fragment
            duration = 300.toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorPrimary))
        }
    }

    override fun ignite(savedInstanceState: Bundle?) {
        args.also {
            binding?.apply {
                setVariable(BR.newsDetail,it.news)
            }
        }
//        arguments?.getParcelable<Permission>("permission")?.let {
//            binding?.apply {
//                setScreenTitle("Permissions", it.name)
//                setVariable(BR.permission, it)
//                agreeBtn.tag = it
//            }
//        }
    }


}

