package com.mtnine.dohwaji.view

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationBarView
import com.mtnine.dohwaji.R
import com.mtnine.dohwaji.base.BaseActivity
import com.mtnine.dohwaji.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }),
    NavigationBarView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Dohwaji)
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().add(R.id.linearLayout, RecyclerFragment())
            .commit()

        binding.bottomNav.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_list -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.linearLayout, RecyclerFragment()).commitAllowingStateLoss()
                return true
            }
            R.id.menu_post -> {
                supportFragmentManager.beginTransaction().replace(R.id.linearLayout, PostFragment())
                    .commitAllowingStateLoss()
                return true
            }
            R.id.menu_mirror -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.linearLayout, CameraFragment()).commitAllowingStateLoss()
                return true
            }
        }
        return false
    }
}