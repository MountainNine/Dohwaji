package com.mtnine.dohwaji.view

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BaseActivity
import com.mtnine.dohwaji.databinding.ActivityPostsBinding
import com.mtnine.dohwaji.model.Post
import com.mtnine.dohwaji.view.adapter.PostListAdapter
import com.mtnine.dohwaji.view.adapter.RecyclerListAdapter
import com.mtnine.dohwaji.view.listener.OnPostEditListener
import com.mtnine.dohwaji.vm.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsActivity : BaseActivity<ActivityPostsBinding>(R.layout.activity_posts), OnPostEditListener {
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = PostListAdapter(this)
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter
        binding.toolbar.back.setOnClickListener { finish() }
        binding.add.setOnClickListener {
            val post = Post(text = binding.edit.text.toString())
            viewModel.insertPost(post).invokeOnCompletion {
                binding.edit.text?.clear()
                refreshData()
            }
        }
        lifecycleScope.launch {
            viewModel.getPostPagingData().collect {
                (binding.list.adapter as PostListAdapter).submitData(it)
            }
        }
    }

    fun refreshData() {
        (binding.list.adapter as PostListAdapter).refresh()
    }

    override fun onPostEditStart() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.showSoftInput(view, 0)
        }
    }

    override fun onPostEditEnd(post: Post) {
        viewModel.updatePost(post)
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onPostDelete(post: Post) {
        viewModel.deletePost(post).invokeOnCompletion {
            refreshData()
        }
    }
}