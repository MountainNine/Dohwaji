package com.mtnine.dohwaji.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BasePagingAdapter
import com.mtnine.dohwaji.databinding.LayoutItemBinding

class RecyclerListAdapter() :
    BasePagingAdapter<String, LayoutItemBinding>(diffCallback) {

    override fun onCreate(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): LayoutItemBinding = LayoutItemBinding.inflate(inflater, parent, false)

    override fun onBind(binding: LayoutItemBinding, item: String, position: Int) {
        binding.text.text = item
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}