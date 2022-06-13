package com.mtnine.dohwaji.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BaseFragment
import com.mtnine.dohwaji.databinding.FragmentRecyclerBinding
import com.mtnine.dohwaji.view.adapter.RecyclerListAdapter
import com.mtnine.dohwaji.vm.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecyclerFragment : BaseFragment<FragmentRecyclerBinding>() {
    private val viewModel: MyViewModel by viewModels()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecyclerBinding = FragmentRecyclerBinding.inflate(inflater, container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val adapter = RecyclerListAdapter()
        binding.list.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.getRecyclerPagingData().collect {
                (binding.list.adapter as RecyclerListAdapter).submitData(it)
            }
        }
        return binding.root
    }
}