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
    //region Members and Props
    override val layoutResourceId = R.layout.fragment_news_detail
    override var shouldBindData=true
    private val args:NewsDetailArgs by navArgs()
    //endregion

    //region Fragment LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.nav_host_fragment
            duration = 500.toLong()
            scrimColor = requireContext().themeColor(R.attr.colorSecondary)
            setAllContainerColors(requireContext().themeColor(R.attr.colorPrimary))
        }
    }
    //endregion


    //region Implementation
    override fun ignite(savedInstanceState: Bundle?) {
        args.also {
            setScreenTitle(it.news.title,getString(R.string.most_popular))
            binding?.apply {
                setVariable(BR.newsDetail,it.news)
            }
        }
    }
    //endregion



}

