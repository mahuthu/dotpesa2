package com.example.smscallcapture.ui.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.AdapterView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.smscallcapture.databinding.ActivitySettingsBinding
import com.example.smscallcapture.network.NetworkManager
import com.example.smscallcapture.utils.SettingsManager
import com.example.smscallcapture.utils.UrlUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var settings: SettingsManager
    private val networkManager = NetworkManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        settings = SettingsManager(this)

        setupProtocolSpinner()
        loadValues()
        setupListeners()
    }

    private fun setupProtocolSpinner() {
        val protocols = listOf("http", "https")
        binding.spinnerProtocol.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, protocols)
        val idx = protocols.indexOf(settings.getProtocol())
        binding.spinnerProtocol.setSelection(if (idx >= 0) idx else 1)
    }

    private fun loadValues() {
        binding.editHost.setText(settings.getHost())
        binding.editPort.setText(settings.getPort())
        binding.editBranchId.setText(settings.getBranchId())
        binding.editModemId.setText(settings.getModemId())
        binding.editInterval.setText(settings.getSyncInterval().toString())
        binding.switchToasts.isChecked = settings.isShowToastsEnabled()
        binding.switchSoundReceive.isChecked = settings.isSmsReceiveSoundEnabled()
        binding.switchSoundUpload.isChecked = settings.isSmsUploadSoundEnabled()
        binding.textBaseUrl.text = settings.getBaseUrl()
    }

    private fun setupListeners() {
        binding.spinnerProtocol.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val protocol = parent.getItemAtPosition(position) as String
                settings.setProtocol(protocol)
                recomputeBaseUrl()
            }
            override fun onNothingSelected(parent: AdapterView<*>) { }
        }
        binding.editHost.addTextChangedListener { text ->
            settings.setHost(text?.toString()?.trim() ?: "")
            recomputeBaseUrl()
        }
        binding.editPort.addTextChangedListener { text ->
            settings.setPort(text?.toString()?.trim() ?: "")
            recomputeBaseUrl()
        }
        binding.editBranchId.addTextChangedListener { text -> settings.setBranchId(text?.toString() ?: "") }
        binding.editModemId.addTextChangedListener { text -> settings.setModemId(text?.toString() ?: "") }
        binding.editInterval.addTextChangedListener { text ->
            val value = text?.toString()?.toIntOrNull() ?: 300
            settings.setSyncInterval(value)
        }
        binding.switchToasts.setOnCheckedChangeListener { _, isChecked -> settings.setShowToasts(isChecked) }
        binding.switchSoundReceive.setOnCheckedChangeListener { _, isChecked -> settings.setSmsReceiveSound(isChecked) }
        binding.switchSoundUpload.setOnCheckedChangeListener { _, isChecked -> settings.setSmsUploadSound(isChecked) }

        binding.buttonTestConnection.setOnClickListener { testConnection() }
        binding.buttonClearUrl.setOnClickListener { clearUrlAndReturn() }
    }

    private fun recomputeBaseUrl() {
        val protocol = settings.getProtocol()
        val host = settings.getHost()
        val port = settings.getPort().ifEmpty { if (protocol == "https") "443" else "80" }
        if (host.isEmpty() || !UrlUtils.isValidHost(host) || !UrlUtils.isValidPort(port)) {
            binding.textBaseUrl.text = ""
            return
        }
        val base = UrlUtils.buildBaseUrl(protocol, host, port)
        settings.setBaseUrl(base)
        binding.textBaseUrl.text = base
    }

    private fun testConnection() {
        val baseUrl = settings.getBaseUrl()
        if (baseUrl.isEmpty()) {
            Toast.makeText(this, "Base URL not set", Toast.LENGTH_SHORT).show()
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val api = networkManager.getApiService(baseUrl)
                runOnUiThread { Toast.makeText(this@SettingsActivity, "Client ready for $baseUrl", Toast.LENGTH_SHORT).show() }
            } catch (e: Exception) {
                runOnUiThread { Toast.makeText(this@SettingsActivity, "Connection failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show() }
            }
        }
    }

    private fun clearUrlAndReturn() {
        settings.setProtocol("https")
        settings.setHost("")
        settings.setPort("")
        settings.setBaseUrl("")
        binding.textBaseUrl.text = ""
        Toast.makeText(this, "Base URL cleared", Toast.LENGTH_SHORT).show()
        // Return to main; Web tab will show welcome overlay due to empty base URL
        startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
