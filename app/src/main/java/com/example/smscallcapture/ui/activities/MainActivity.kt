package com.example.smscallcapture.ui.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.smscallcapture.R
import com.example.smscallcapture.SmsCallApplication
import com.example.smscallcapture.databinding.ActivityMainBinding
import com.example.smscallcapture.ui.adapters.MainPagerAdapter
import com.example.smscallcapture.ui.fragments.CallsFragment
import com.example.smscallcapture.ui.fragments.SmsFragment
import com.google.android.material.tabs.TabLayoutMediator
import android.content.res.ColorStateList
import android.graphics.Color

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pagerAdapter: MainPagerAdapter
    
    private val requiredPermissions = arrayOf(
        Manifest.permission.RECEIVE_SMS,
        Manifest.permission.READ_SMS,
        Manifest.permission.READ_CALL_LOG,
        Manifest.permission.READ_PHONE_STATE
    )
    
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val deniedPermissions = permissions.entries.filter { !it.value }
        if (deniedPermissions.isNotEmpty()) {
            Toast.makeText(this, "Some permissions were denied. App functionality may be limited.", Toast.LENGTH_LONG).show()
        } else {
            // Re-register call observer now that permission may be granted
            (application as SmsCallApplication).ensureCallObserverRegistered()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setLogo(R.drawable.dotpesa)
            setDisplayUseLogoEnabled(true)
        }
        
        setupViewPager()
        checkPermissions()
        
        // Hide toolbar initially since we start on Web tab (position 0)
        supportActionBar?.hide()
    }

    override fun onBackPressed() {
        // Disable back navigation; do nothing
    }

    override fun onResume() {
        super.onResume()
        if (::pagerAdapter.isInitialized) pagerAdapter.reloadWebView()
    }
    
    private fun setupViewPager() {
        pagerAdapter = MainPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter
        
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val tabView = layoutInflater.inflate(R.layout.tab_item, null)
            val icon = tabView.findViewById<ImageView>(R.id.tabIcon)
            val text = tabView.findViewById<TextView>(R.id.tabText)
            when (position) {
                0 -> { icon.setImageResource(android.R.drawable.ic_menu_view); text.text = getString(R.string.tab_web) }
                1 -> { icon.setImageResource(android.R.drawable.sym_action_call); text.text = getString(R.string.tab_calls) }
                2 -> { icon.setImageResource(android.R.drawable.ic_dialog_email); text.text = getString(R.string.tab_sms) }
            }
            tab.customView = tabView
        }.attach()
        
        val dotPesaGreen = ColorStateList.valueOf(Color.parseColor("#1E6636"))
        binding.tabLayout.tabIconTint = dotPesaGreen
        
        binding.viewPager.registerOnPageChangeCallback(object : androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) supportActionBar?.hide() else supportActionBar?.show()
            }
        })
    }

    private fun checkPermissions() {
        val permissionsToRequest = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()
        if (permissionsToRequest.isNotEmpty()) {
            permissionLauncher.launch(permissionsToRequest)
        } else {
            (application as SmsCallApplication).ensureCallObserverRegistered()
        }
    }

    private fun routeFilterToCurrentFragment(filter: String) {
        val currentItem = binding.viewPager.currentItem
        val frag: Fragment? = supportFragmentManager.findFragmentByTag("f$currentItem")
        when (frag) {
            is CallsFragment -> frag.setStatusFilter(filter)
            is SmsFragment -> frag.setStatusFilter(filter)
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> { startActivity(Intent(this, SettingsActivity::class.java)); true }
            R.id.action_license -> { startActivity(Intent(this, LicenseActivity::class.java)); true }
            R.id.action_support -> { startActivity(Intent(this, SupportActivity::class.java)); true }
            R.id.action_filter_all -> { routeFilterToCurrentFragment("ALL"); true }
            R.id.action_filter_pending -> { routeFilterToCurrentFragment("PENDING"); true }
            R.id.action_filter_failed -> { routeFilterToCurrentFragment("FAILED"); true }
            R.id.action_filter_uploaded -> { routeFilterToCurrentFragment("UPLOADED"); true }
            R.id.action_exit -> { finishAffinity(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }
}









