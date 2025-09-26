package com.example.smscallcapture.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.smscallcapture.ui.fragments.CallsFragment
import com.example.smscallcapture.ui.fragments.SmsFragment
import com.example.smscallcapture.ui.fragments.WebFragment

class MainPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private var webFragment: WebFragment? = null
    
    override fun getItemCount(): Int = 3
    
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                webFragment = WebFragment()
                webFragment!!
            }
            1 -> CallsFragment()
            2 -> SmsFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
    
    fun reloadWebView() {
        webFragment?.reloadWebView()
    }
}




















