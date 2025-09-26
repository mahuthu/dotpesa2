package com.example.smscallcapture.ui.fragments

import android.app.AlertDialog
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Telephony
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smscallcapture.R
import com.example.smscallcapture.SmsCallApplication
import com.example.smscallcapture.data.models.SmsEntity
import com.example.smscallcapture.databinding.FragmentSmsBinding
import com.example.smscallcapture.services.SyncService
import com.example.smscallcapture.ui.adapters.SmsAdapter
import com.example.smscallcapture.ui.viewmodels.SmsViewModel
import com.example.smscallcapture.utils.SettingsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class SmsFragment : Fragment() {
    private var _binding: FragmentSmsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SmsViewModel by viewModels()
    private val adapter = SmsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerSms.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerSms.adapter = adapter
        
        val statuses = resources.getStringArray(R.array.status_filter)
        binding.spinnerStatus.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, statuses)
        binding.spinnerStatus.setSelection(0)
        binding.spinnerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
                viewModel.setStatusFilter(statuses[position])
            }
            override fun onNothingSelected(parent: AdapterView<*>) { }
        }

        binding.fabImportSms.setOnClickListener { showImportDialog() }

        viewModel.sms.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    private fun showImportDialog() {
        val input = EditText(requireContext()).apply { hint = getString(R.string.import_sms_hint) }
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.import_sms_title)
            .setView(input)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.import_sms_action) { _, _ ->
                val text = input.text?.toString()?.trim().orEmpty()
                val millis = parseDateToMillis(text)
                if (millis == null) {
                    Toast.makeText(requireContext(), "Invalid date format", Toast.LENGTH_LONG).show()
                    return@setPositiveButton
                }
                importSmsFrom(millis)
            }
            .show()
    }

    private fun parseDateToMillis(dateStr: String): Long? {
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            sdf.isLenient = false
            val d = sdf.parse(dateStr)
            d?.time
        } catch (_: Exception) { null }
    }

    private fun importSmsFrom(startMillis: Long) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(), "SMS permission not granted", Toast.LENGTH_LONG).show()
            return
        }
        val app = requireContext().applicationContext as SmsCallApplication
        val settings = SettingsManager(requireContext())
        val branchId = settings.getBranchId()
        val deviceId = settings.getModemId()
        val cr = requireContext().contentResolver

        CoroutineScope(Dispatchers.IO).launch {
            val uri = Telephony.Sms.Inbox.CONTENT_URI
            val projection = arrayOf(Telephony.Sms.ADDRESS, Telephony.Sms.BODY, Telephony.Sms.DATE)
            val selection = "${Telephony.Sms.DATE}>=?"
            val args = arrayOf(startMillis.toString())
            val sort = "${Telephony.Sms.DATE} ASC"
            val now = System.currentTimeMillis()
            var imported = 0
            try {
                cr.query(uri, projection, selection, args, sort)?.use { cursor ->
                    val idxAddress = cursor.getColumnIndex(Telephony.Sms.ADDRESS)
                    val idxBody = cursor.getColumnIndex(Telephony.Sms.BODY)
                    val idxDate = cursor.getColumnIndex(Telephony.Sms.DATE)
                    while (cursor.moveToNext()) {
                        val sender = cursor.getString(idxAddress) ?: "Unknown"
                        val body = cursor.getString(idxBody) ?: ""
                        val date = cursor.getLong(idxDate)
                        val entity = SmsEntity(
                            sender = sender,
                            message = body,
                            receivedDate = date,
                            status = "PENDING",
                            branchId = branchId,
                            deviceId = deviceId,
                            createdAt = now,
                            updatedAt = now
                        )
                        app.repository.insertSms(entity)
                        imported++
                    }
                }
            } catch (_: SecurityException) {
                // Permission issue handled above
            }
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(requireContext(), "Imported $imported SMS", Toast.LENGTH_LONG).show()
                SyncService.startSync(requireContext(), SyncService.TARGET_SMS)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
