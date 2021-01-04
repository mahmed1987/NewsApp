package com.naggaro.newsapp.news.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.core.view.doOnPreDraw
import androidx.databinding.BindingAdapter
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.transition.MaterialElevationScale
import com.naggaro.common.newsapp.base.BaseFragment
import com.naggaro.common.newsapp.base.BaseViewModel
import com.naggaro.common.newsapp.base.GeneralAdapter
import com.naggaro.common.newsapp.extensions.configureVerticalList
import com.naggaro.common.newsapp.extensions.fault
import com.naggaro.common.newsapp.extensions.observe
import com.naggaro.dtos.news.NewsView
import com.naggaro.dtos.news.PictureView
import com.naggaro.newsapp.news.BR
import com.naggaro.newsapp.news.R
import com.naggaro.newsapp.news.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FakeNewsList : BaseFragment() {
    override fun layoutResourceId() = R.layout.fragment_news_list

    private lateinit var adapter: GeneralAdapter<NewsView>
    private val newsViewModel: NewsViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(R.id.newsNavigation)
    })
    override fun ignite(savedInstanceState: Bundle?) {
        adapter = GeneralAdapter(BR.news,R.layout.news_item, NewsView.DIFF_CALLBACK)
        adapter.clickListener={ news,view->
            findNavController().navigate(NewsListFragmentDirections.toDetail(news))
        }
    }
    fun populateData(news:List<NewsView>)
    {
        newsRv.configureVerticalList(adapter)
        adapter.submitList(news)
    }
}

