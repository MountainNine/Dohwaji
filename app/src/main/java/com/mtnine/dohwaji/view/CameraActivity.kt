package com.mtnine.dohwaji.view

import android.os.Bundle
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BaseActivity
import com.mtnine.dohwaji.databinding.ActivityCameraBinding
import com.mtnine.dohwaji.vm.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraActivity : BaseActivity<ActivityCameraBinding>(R.layout.activity_camera) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}