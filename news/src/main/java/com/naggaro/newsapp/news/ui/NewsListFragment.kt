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

class NewsListFragment() : BaseFragment() {

    //region Members and Props

    private val adapter = GeneralAdapter(BR.news, R.layout.news_item, NewsView.DIFF_CALLBACK)
    //endregion

    //region Injections
    private val newsViewModel: NewsViewModel by sharedViewModel(from = {
        findNavController().getViewModelStoreOwner(R.id.newsNavigation)
    }) // passing the viewmodelstore here binds the lifecycle of this viewmodel with the navigation graph passed to it. As soon as the navigation graph is destroyed the viewmodel is also killed.
    //endregion
    //region Fragment Lifecycle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel.fetchMostViewedNews("all-sections", 7)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Postpone enter transitions to allow shared element transitions to run.
        // https://github.com/googlesamples/android-architecture-components/issues/495
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.news_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                newsViewModel.fetchMostViewedNews("all-sections", 7)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun layoutResourceId()= R.layout.fragment_news_list
    override fun attachListeners() {
        super.attachListeners()
        adapter.clickListener={ news,view->
            exitTransition = MaterialElevationScale(false).apply {
                duration = 400L
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = 400L
            }
            val newsItemDetailTransitionName = getString(R.string.news_item_detail_transition_name)
            val extras = FragmentNavigatorExtras(view to newsItemDetailTransitionName)
            findNavController().navigate(NewsListFragmentDirections.toDetail(news),extras)
        }
    }
    override fun ignite(savedInstanceState: Bundle?) {
        newsRv.configureVerticalList(adapter)
        setScreenTitle(getString(R.string.ny_times),getString(R.string.most_popular))
        newsViewModel.run {

            observe(mostViewedNews)
            {
                //only commit the largest picture from the array , so as to smoothly show the transitions between this screen and the detail
                it.forEach {
                    val largestPicture = PictureUtil.findLargestImage(it.pictures)
                    it.largestPicture = largestPicture
                }
                populateList(it)
            }
            observe(operationStatus)
            {
                it?.let {
                    when (it) {
                        BaseViewModel.Operation.STARTED -> showProgress(true, true)
                        BaseViewModel.Operation.COMPLETED -> showProgress(false, false)
                    }
                }
            }
            fault(failure, ::handleFailure)
        }
    }

     fun populateList(it: List<NewsView>) {
        adapter.submitList(it)
    }

}





