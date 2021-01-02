package com.naggaro.common.newsapp.extensions

import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*

enum class MarginStrategy {
    ONLY_TOP,
    ONLY_RIGHT,
    ONLY_BOTTOM,
    ALL_BUT_TOP,
    ALL_BUT_BOTTOM,
    ALL_BUT_TOP_AND_BOTTOM,
    ALL_SIDES,
}

fun RecyclerView.addItemMargins(
    spaceHeight: Int,
    marginStrategy: MarginStrategy = MarginStrategy.ONLY_TOP
) {

    addItemDecoration(MarginItemDecoration(spaceHeight, marginStrategy))
}

fun <T, VH : RecyclerView.ViewHolder> RecyclerView.configureStaggeredList(adapter: ListAdapter<T, VH>,spanCount:Int=2) {

    layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
    setHasFixedSize(true)
    addItemMargins(10.toPx(), MarginStrategy.ALL_SIDES)
    this.adapter = adapter

}
fun <T, VH : RecyclerView.ViewHolder> RecyclerView.configureGridList(adapter: ListAdapter<T, VH>,spanCount:Int=2) {

    layoutManager = GridLayoutManager(context,spanCount)
    setHasFixedSize(true)
    addItemMargins(10.toPx(), MarginStrategy.ALL_SIDES)
    this.adapter = adapter

}


fun < VH : RecyclerView.ViewHolder> RecyclerView.configureStaggeredList(adapter: RecyclerView.Adapter<VH>,spanCount:Int=2) {

    layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
    setHasFixedSize(true)
    addItemMargins(10.toPx(), MarginStrategy.ALL_SIDES)
    this.adapter = adapter

}

fun <T, VH : RecyclerView.ViewHolder> RecyclerView.configureHorizontalList(adapter: ListAdapter<T, VH>) {

    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    addItemMargins(10.toPx(), MarginStrategy.ONLY_RIGHT)
    this.adapter = adapter

}

fun <VH : RecyclerView.ViewHolder> RecyclerView.configureHorizontalList(adapter: RecyclerView.Adapter<VH>) {

    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    addItemMargins(10.toPx(), MarginStrategy.ONLY_RIGHT)
    this.adapter = adapter
}

fun <T, VH : RecyclerView.ViewHolder> RecyclerView.configureVerticalList(
    adapter: ListAdapter<T, VH>,
    margin: Int = 10.toPx()
) {

    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    setHasFixedSize(true)
    addItemMargins(margin, MarginStrategy.ONLY_BOTTOM)
    this.adapter = adapter

}

fun <VH : RecyclerView.ViewHolder> RecyclerView.configureVerticalList(
    adapter: RecyclerView.Adapter<VH>,
    margin: Int = 10.toPx()
) {

    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    setHasFixedSize(true)
    addItemMargins(margin, MarginStrategy.ONLY_BOTTOM)
    this.adapter = adapter

}

class MarginItemDecoration(
    private val spaceHeight: Int,
    private val marginStrategy: MarginStrategy
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount

        when (marginStrategy) {
            MarginStrategy.ONLY_TOP -> with(outRect) { top = spaceHeight }
            MarginStrategy.ONLY_RIGHT -> with(outRect) { right = spaceHeight }
            MarginStrategy.ALL_BUT_TOP -> if (position > 0) with(outRect) { top = spaceHeight }
            MarginStrategy.ALL_SIDES -> if (position > -1) with(outRect) {
                top = spaceHeight
                left = spaceHeight
                right = spaceHeight
                bottom = spaceHeight
            }
            MarginStrategy.ONLY_BOTTOM -> with(outRect) { bottom = spaceHeight }
            MarginStrategy.ALL_BUT_BOTTOM -> TODO()
            MarginStrategy.ALL_BUT_TOP_AND_BOTTOM -> TODO()
        }

    }
}


class BreakSpanDecoration(private val breakDistance: Int, private val spaceHeight: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position % 5 == 4) {
            with(outRect) {
                top = -breakDistance
                right = spaceHeight
                left = spaceHeight
            }
        } else {
            with(outRect) {
                right = spaceHeight
                left = spaceHeight
            }
        }
    }

}
