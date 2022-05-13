package com.mtnine.dohwaji.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BaseActivity
import com.mtnine.dohwaji.databinding.ActivityRecyclerBinding
import com.mtnine.dohwaji.view.adapter.RecyclerListAdapter
import com.mtnine.dohwaji.vm.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecyclerActivity : BaseActivity<ActivityRecyclerBinding>(R.layout.activity_recycler) {
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = RecyclerListAdapter()
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter

        viewModel.getWord()
        viewModel.getWord.observe(this) {
            adapter.items = ArrayList(it.words)
            adapter.notifyDataSetChanged()
        }
    }
}