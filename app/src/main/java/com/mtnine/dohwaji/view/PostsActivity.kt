package com.mtnine.dohwaji.view

import android.os.Bundle
import androidx.activity.viewModels
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BaseActivity
import com.mtnine.dohwaji.databinding.ActivityPostsBinding
import com.mtnine.dohwaji.vm.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsActivity : BaseActivity<ActivityPostsBinding>(R.layout.activity_posts) {
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}