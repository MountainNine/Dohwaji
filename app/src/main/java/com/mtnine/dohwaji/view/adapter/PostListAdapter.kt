package com.mtnine.dohwaji.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BasePagingAdapter
import com.mtnine.dohwaji.databinding.LayoutPostBinding
import com.mtnine.dohwaji.model.Post
import com.mtnine.dohwaji.view.listener.OnPostEditListener

class PostListAdapter(private val onPostEditListener: OnPostEditListener) :
    BasePagingAdapter<Post, LayoutPostBinding>(diffCallback) {

    override fun onCreate(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = LayoutPostBinding.inflate(inflater, parent, false)

    override fun onBind(binding: LayoutPostBinding, item: Post, position: Int) {
        var editMode = false
        binding.text.setText(item.text)
        binding.edit.setOnClickListener {
            if (!editMode) {
                Glide.with(binding.root).load(R.drawable.ic_baseline_check_24)
                    .into(binding.edit)
                binding.text.isClickable = true
                binding.text.requestFocus()
                editMode = true
                onPostEditListener.onPostEditStart()
            } else {
                Glide.with(binding.root).load(R.drawable.ic_baseline_edit_24)
                    .into(binding.edit)
                binding.text.isClickable = false
                editMode = false
                item.text = binding.text.text.toString()
                onPostEditListener.onPostEditEnd(item)
                binding.text.clearFocus()
            }
        }

        binding.delete.setOnClickListener {
            onPostEditListener.onPostDelete(item)
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