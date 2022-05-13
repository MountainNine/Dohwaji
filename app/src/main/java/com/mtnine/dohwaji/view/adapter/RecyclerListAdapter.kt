package com.mtnine.dohwaji.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.databinding.LayoutItemBinding

class RecyclerListAdapter : RecyclerView.Adapter<RecyclerListAdapter.RecyclerListViewHolder>() {
    var items = ArrayList<String>()

    inner class RecyclerListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return RecyclerListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
        val item = items[position]
        holder.binding.text.text = item
    }

    override fun getItemCount(): Int {
        return items.size
    }


}