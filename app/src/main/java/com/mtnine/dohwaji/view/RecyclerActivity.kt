package com.mtnine.dohwaji.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BaseActivity
import com.mtnine.dohwaji.databinding.ActivityRecyclerBinding
import com.mtnine.dohwaji.vm.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecyclerActivity : BaseActivity<ActivityRecyclerBinding>(R.layout.activity_recycler) {
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}