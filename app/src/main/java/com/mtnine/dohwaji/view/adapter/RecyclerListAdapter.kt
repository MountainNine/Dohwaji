package com.mtnine.dohwaji.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.databinding.LayoutItemBinding

class RecyclerListAdapter() :
    PagingDataAdapter<String, RecyclerListAdapter.RecyclerListViewHolder>(diffCallback) {

    inner class RecyclerListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return RecyclerListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.text.text = item
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