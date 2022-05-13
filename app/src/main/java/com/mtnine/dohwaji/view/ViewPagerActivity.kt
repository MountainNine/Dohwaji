package com.mtnine.dohwaji.view

import android.os.Bundle
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BaseActivity
import com.mtnine.dohwaji.databinding.ActivityViewpagerBinding
import com.mtnine.dohwaji.vm.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerActivity : BaseActivity<ActivityViewpagerBinding>(R.layout.activity_viewpager) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}