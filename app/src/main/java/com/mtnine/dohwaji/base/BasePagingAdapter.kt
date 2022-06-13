package com.mtnine.dohwaji.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BasePagingAdapter<T : Any, VDB : ViewDataBinding>(val callback: DiffUtil.ItemCallback<T>) :
    PagingDataAdapter<T, BasePagingAdapter.BindingViewHolder<VDB>>(callback) {
    class BindingViewHolder<VDB : ViewDataBinding>(val binding: VDB) :
        RecyclerView.ViewHolder(binding.root)

    abstract fun onCreate(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VDB

    abstract fun onBind(binding: VDB, item: T, position: Int)
    open fun onBindPlaceHolder(binding: VDB, position: Int) {

    }

    override fun onBindViewHolder(holder: BindingViewHolder<VDB>, position: Int) {
        val item = getItem(position)
        if (item != null) {
            onBind(holder.binding, item, position)
        } else {
            onBindPlaceHolder(holder.binding, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BindingViewHolder(onCreate(LayoutInflater.from(parent.context), parent, viewType))
}