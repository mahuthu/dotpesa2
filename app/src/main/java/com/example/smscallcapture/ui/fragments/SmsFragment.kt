package com.example.smscallcapture.ui.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
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
import androidx.appcompat.widget.SearchView
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
import java.util.Calendar

class SmsFragment : Fragment() {
    private var _binding: FragmentSmsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SmsViewModel by viewModels()
    private val adapter = SmsAdapter()
    private var fullList: List<SmsEntity> = emptyList()

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
        
        // Search instead of spinner
        binding.fabRefreshSms.setOnClickListener { SyncService.startSync(requireContext(), SyncService.TARGET_SMS) }
        binding.fabImportSms.setOnClickListener { showDatePicker() }

        viewModel.sms.observe(viewLifecycleOwner) { list ->
            fullList = list
            adapter.submitList(list)
        }

        binding.searchSms.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { filterList(query); return true }
            override fun onQueryTextChange(newText: String?): Boolean { filterList(newText); return true }
        })
    }

    private fun filterList(query: String?) {
        val q = (query ?: "").trim().lowercase()
        if (q.isEmpty()) { adapter.submitList(fullList); return }
        val filtered = fullList.filter { sms ->
            sms.sender.lowercase().contains(q) ||
            sms.message.lowercase().contains(q) ||
            sms.receivedDate.toString().contains(q)
        }
        adapter.submitList(filtered)
    }

    fun setStatusFilter(status: String) { viewModel.setStatusFilter(status) }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        val dlg = DatePickerDialog(requireContext(), { _, y, m, d ->
            val pickedCal = Calendar.getInstance().apply { set(y, m, d, 0, 0, 0); set(Calendar.MILLISECOND, 0) }
            importSmsFrom(pickedCal.timeInMillis)
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
        dlg.show()
    }

    private fun importSmsFrom(startMillis: Long) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(), "SMS permission not granted", Toast.LENGTH_LONG).show(); return
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
                            uploadedDate = null,
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
            } catch (_: SecurityException) { }
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(requireContext(), "Imported $imported SMS", Toast.LENGTH_LONG).show()
                SyncService.startSync(requireContext(), SyncService.TARGET_SMS)
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
