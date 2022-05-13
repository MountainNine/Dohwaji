package com.mtnine.dohwaji.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BaseActivity
import com.mtnine.dohwaji.databinding.ActivityMainBinding
import com.mtnine.dohwaji.vm.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    View.OnClickListener {

    val BASE_URL = "https://a5mppwt298.execute-api.ap-northeast-2.amazonaws.com/dohwaji/getword"
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding.toolbar.back.visibility = View.GONE
        binding.recycler.setOnClickListener(this)
        binding.posts.setOnClickListener(this)
        binding.viewpager.setOnClickListener(this)
        binding.camera.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val nextActivity = when (v) {
            binding.recycler -> RecyclerActivity::class.java // Retrofit + Coroutine + Paging
            binding.posts -> PostsActivity::class.java // Room
            binding.viewpager -> ViewPagerActivity::class.java // ViewPager + BottomNavigation
            binding.camera -> CameraActivity::class.java // CameraX
            else -> null
        }

        if (nextActivity != null) {
            startActivity(Intent(this, nextActivity))
        }
    }
}