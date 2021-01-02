package com.naggaro.common.newsapp.base

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.naggaro.common.newsapp.extensions.dataBind
import kotlin.properties.Delegates


class GeneralAdapter<T>(
    private val variableId: Int,
    private val layoutResource: Int,
    diffCallback: DiffUtil.ItemCallback<T>,
    private val listOfClickableIds: List<Int> = mutableListOf()

) :
    ListAdapter<T, GeneralAdapter<T>.ViewHolder>(diffCallback) {

    var clickListener: (T,View) -> Unit = {_,_->}
    var clickListenerSpecific: (T, Int) -> Unit = { _, _ -> }
    inner class ViewHolder(private val itemBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {
        init {
            itemBinding.root.setOnClickListener(this)
            if (listOfClickableIds.isNotEmpty())
                listOfClickableIds.map {
                    itemBinding.root.findViewById<View>(it).setOnClickListener(this)
                }

        }

        fun bind(item: T) {
            itemBinding.setVariable(variableId, item)
            itemBinding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            (getItem(layoutPosition)).run {
                if (listOfClickableIds.isEmpty())
                    clickListener(this,v!!)
                else
                    clickListenerSpecific(this, v!!.id)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.dataBind(layoutResource))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))


}
