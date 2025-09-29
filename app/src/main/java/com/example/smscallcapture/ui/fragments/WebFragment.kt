package com.example.smscallcapture.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.smscallcapture.databinding.FragmentWebBinding
import com.example.smscallcapture.ui.activities.QRScannerActivity
import com.example.smscallcapture.utils.SettingsManager

class WebFragment : Fragment() {
    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!
    private lateinit var settingsManager: SettingsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        settingsManager = SettingsManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient() {}
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: android.webkit.WebView?, newProgress: Int) {
                binding.progressBar.visibility = if (newProgress in 1..99) View.VISIBLE else View.GONE
                binding.progressBar.progress = newProgress
            }
        }

        binding.fabReload.setOnClickListener { reloadWebView() }
        binding.fabScan.setOnClickListener { startActivity(Intent(requireContext(), QRScannerActivity::class.java)) }

        reloadWebView()
    }

    fun reloadWebView() {
        val url = settingsManager.getBaseUrl()
        val hasUrl = url.isNotEmpty()
        binding.welcomeOverlay.visibility = if (hasUrl) View.GONE else View.VISIBLE
        if (hasUrl) {
            binding.progressBar.progress = 0
            binding.progressBar.visibility = View.VISIBLE
            binding.webView.loadUrl(url)
        }
    }

    override fun onResume() {
        super.onResume()
        reloadWebView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}










