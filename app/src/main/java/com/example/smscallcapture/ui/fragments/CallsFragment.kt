package com.example.smscallcapture.ui.fragments

import android.Manifest
import android.app.DatePickerDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.CallLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.example.smscallcapture.SmsCallApplication
import com.example.smscallcapture.data.models.CallEntity
import com.example.smscallcapture.databinding.FragmentCallsBinding
import com.example.smscallcapture.services.SyncService
import com.example.smscallcapture.ui.adapters.CallsAdapter
import com.example.smscallcapture.ui.viewmodels.CallViewModel
import com.example.smscallcapture.utils.SettingsManager
import com.example.smscallcapture.R
import java.util.Calendar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CallsFragment : Fragment() {
    private var _binding: FragmentCallsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CallViewModel by viewModels()
    private val adapter = CallsAdapter()
    private var fullList: List<CallEntity> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCallsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerCalls.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerCalls.adapter = adapter

        binding.fabRefreshCalls.setOnClickListener { SyncService.startSync(requireContext(), SyncService.TARGET_CALLS) }
        binding.fabImportCalls.setOnClickListener { showDatePicker() }

        viewModel.calls.observe(viewLifecycleOwner) { list ->
            fullList = list
            adapter.submitList(list)
        }

        binding.searchCalls.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { filterList(query); return true }
            override fun onQueryTextChange(newText: String?): Boolean { filterList(newText); return true }
        })
    }

    private fun filterList(query: String?) {
        val q = (query ?: "").trim().lowercase()
        if (q.isEmpty()) { adapter.submitList(fullList); return }
        val filtered = fullList.filter { c ->
            c.callerId.lowercase().contains(q) ||
            c.callType.lowercase().contains(q) ||
            c.startTime.toString().contains(q) ||
            (c.endTime?.toString()?.contains(q) ?: false)
        }
        adapter.submitList(filtered)
    }

    fun setStatusFilter(status: String) { viewModel.setStatusFilter(status) }

    private fun showDatePicker() {
        val cal = Calendar.getInstance()
        val dlg = DatePickerDialog(requireContext(), { _, y, m, d ->
            val pickedCal = Calendar.getInstance().apply { set(y, m, d, 0, 0, 0); set(Calendar.MILLISECOND, 0) }
            importCallsFrom(pickedCal.timeInMillis)
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
        dlg.show()
    }

    private fun importCallsFrom(startMillis: Long) {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(requireContext(), "Call log permission not granted", Toast.LENGTH_LONG).show(); return
        }
        val app = requireContext().applicationContext as SmsCallApplication
        val settings = SettingsManager(requireContext())
        val branchId = settings.getBranchId()
        val deviceId = settings.getModemId()
        val cr = requireContext().contentResolver

        CoroutineScope(Dispatchers.IO).launch {
            val projection = arrayOf(
                CallLog.Calls.NUMBER,
                CallLog.Calls.TYPE,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION
            )
            val selection = "${CallLog.Calls.DATE}>=?"
            val args = arrayOf(startMillis.toString())
            val sort = "${CallLog.Calls.DATE} ASC"
            val now = System.currentTimeMillis()
            var imported = 0
            cr.query(CallLog.Calls.CONTENT_URI, projection, selection, args, sort)?.use { cursor ->
                val idxNumber = cursor.getColumnIndex(CallLog.Calls.NUMBER)
                val idxType = cursor.getColumnIndex(CallLog.Calls.TYPE)
                val idxDate = cursor.getColumnIndex(CallLog.Calls.DATE)
                val idxDuration = cursor.getColumnIndex(CallLog.Calls.DURATION)
                while (cursor.moveToNext()) {
                    val number = cursor.getString(idxNumber) ?: "Unknown"
                    val typeInt = cursor.getInt(idxType)
                    val start = cursor.getLong(idxDate)
                    val durationSec = cursor.getLong(idxDuration)
                    val end = if (durationSec > 0) start + (durationSec * 1000L) else null
                    val type = when (typeInt) {
                        CallLog.Calls.INCOMING_TYPE -> "INCOMING"
                        CallLog.Calls.OUTGOING_TYPE -> "OUTGOING"
                        CallLog.Calls.MISSED_TYPE -> "MISSED"
                        else -> "UNKNOWN"
                    }
                    val entity = CallEntity(
                        callerId = number,
                        callType = type,
                        startTime = start,
                        endTime = end,
                        uploadedDate = null,
                        status = "PENDING",
                        branchId = branchId,
                        deviceId = deviceId,
                        createdAt = now,
                        updatedAt = now
                    )
                    app.repository.insertCall(entity)
                    imported++
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(requireContext(), "Imported $imported calls", Toast.LENGTH_LONG).show()
                SyncService.startSync(requireContext(), SyncService.TARGET_CALLS)
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
