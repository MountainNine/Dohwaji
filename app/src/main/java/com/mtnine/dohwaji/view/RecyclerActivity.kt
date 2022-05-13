package com.mtnine.dohwaji.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BaseActivity
import com.mtnine.dohwaji.databinding.ActivityRecyclerBinding
import com.mtnine.dohwaji.view.adapter.RecyclerListAdapter
import com.mtnine.dohwaji.vm.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecyclerActivity : BaseActivity<ActivityRecyclerBinding>(R.layout.activity_recycler) {
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.toolbar.back.setOnClickListener { finish() }

        val adapter = RecyclerListAdapter()
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter

        viewModel.getWord()
        lifecycleScope.launch {
            viewModel.pagingData().collect {
                (binding.list.adapter as RecyclerListAdapter).submitData(it)
            }
        }
    }
}