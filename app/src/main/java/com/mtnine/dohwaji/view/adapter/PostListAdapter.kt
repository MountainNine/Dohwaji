package com.mtnine.dohwaji.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.databinding.LayoutPostBinding
import com.mtnine.dohwaji.model.Post
import com.mtnine.dohwaji.view.listener.OnPostEditListener

class PostListAdapter(private val onPostEditListener: OnPostEditListener) :
    PagingDataAdapter<Post, PostListAdapter.PostListViewHolder>(diffCallback) {

    inner class PostListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = LayoutPostBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_post, parent, false)
        return PostListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        val item = getItem(position)
        var editMode = false
        if (item != null) {
            holder.binding.text.setText(item.text)
            holder.binding.edit.setOnClickListener {
                if (!editMode) {
                    Glide.with(holder.itemView).load(R.drawable.ic_baseline_check_24)
                        .into(holder.binding.edit)
                    holder.binding.text.isClickable = true
                    holder.binding.text.requestFocus()
                    editMode = true
                    onPostEditListener.onPostEditStart()
                } else {
                    Glide.with(holder.itemView).load(R.drawable.ic_baseline_edit_24)
                        .into(holder.binding.edit)
                    holder.binding.text.isClickable = false
                    editMode = false
                    item.text = holder.binding.text.text.toString()
                    onPostEditListener.onPostEditEnd(item)
                    holder.binding.text.clearFocus()
                }
            }

            holder.binding.delete.setOnClickListener {
                onPostEditListener.onPostDelete(item)
            }
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.text == newItem.text
            }
        }
    }

}