package com.example.smscallcapture.ui.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.smscallcapture.databinding.ActivityQrscannerBinding
import com.example.smscallcapture.utils.SettingsManager
import com.example.smscallcapture.utils.UrlUtils
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class QRScannerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQrscannerBinding
    private lateinit var settings: SettingsManager

    private val cameraPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) startScan() else Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
    }

    private val scannerLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            val parsed = UrlUtils.parseServerUrl(result.contents)
            if (parsed == null) {
                Toast.makeText(this, "Invalid URL", Toast.LENGTH_LONG).show()
                return@registerForActivityResult
            }
            settings.setProtocol(parsed.protocol)
            settings.setHost(parsed.host)
            settings.setPort(parsed.port)
            settings.setBaseUrl(parsed.baseUrl)
            Toast.makeText(this, "Server set to ${parsed.baseUrl}", Toast.LENGTH_LONG).show()
            finish()
        } else {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrscannerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        settings = SettingsManager(this)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            startScan()
        } else {
            cameraPermission.launch(Manifest.permission.CAMERA)
        }
    }

    private fun startScan() {
        val options = ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            setPrompt("Scan server URL QR")
            setBeepEnabled(false)
            setOrientationLocked(true)
            setCaptureActivity(CustomCaptureActivity::class.java)
            setBarcodeImageEnabled(false)
        }
        scannerLauncher.launch(options)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
