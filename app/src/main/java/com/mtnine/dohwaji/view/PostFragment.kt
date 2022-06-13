package com.mtnine.dohwaji.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BaseFragment
import com.mtnine.dohwaji.databinding.FragmentPostBinding
import com.mtnine.dohwaji.model.Post
import com.mtnine.dohwaji.util.Utils.toast
import com.mtnine.dohwaji.view.adapter.PostListAdapter
import com.mtnine.dohwaji.view.listener.OnPostEditListener
import com.mtnine.dohwaji.vm.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostFragment : BaseFragment<FragmentPostBinding>(), OnPostEditListener {
    private val viewModel: MyViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPostBinding = FragmentPostBinding.inflate(inflater, container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val adapter = PostListAdapter(this)
        binding.list.adapter = adapter
        binding.add.setOnClickListener {
            if (binding.edit.text.toString().isEmpty()) {
                requireContext().toast(getString(R.string.please_input_text))
            } else {
                val post = Post(text = binding.edit.text.toString())
                viewModel.insertPost(post).invokeOnCompletion {
                    binding.edit.text?.clear()
                    refreshData()
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.getPostPagingData().collect {
                (binding.list.adapter as PostListAdapter).submitData(it)
            }
        }
        return binding.root
    }

    fun refreshData() {
        (binding.list.adapter as PostListAdapter).refresh()
    }

    override fun onPostEditStart() {
        activity?.currentFocus?.let { view ->
            val imm =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.showSoftInput(view, 0)
        }
    }

    override fun onPostEditEnd(post: Post) {
        viewModel.updatePost(post)
        activity?.currentFocus?.let { view ->
            val imm =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onPostDelete(post: Post) {
        viewModel.deletePost(post).invokeOnCompletion {
            refreshData()
        }
    }
}