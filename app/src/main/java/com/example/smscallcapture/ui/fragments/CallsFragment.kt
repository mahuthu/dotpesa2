package com.example.smscallcapture.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smscallcapture.R
import com.example.smscallcapture.databinding.FragmentCallsBinding
import com.example.smscallcapture.services.SyncService
import com.example.smscallcapture.ui.adapters.CallsAdapter
import com.example.smscallcapture.ui.viewmodels.CallViewModel

class CallsFragment : Fragment() {
    private var _binding: FragmentCallsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CallViewModel by viewModels()
    private val adapter = CallsAdapter()
    private var fullList: List<com.example.smscallcapture.data.models.CallEntity> = emptyList()

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

        // Remove spinner: now handled via overflow menu
        binding.fabRefreshCalls.setOnClickListener {
            SyncService.startSync(requireContext(), SyncService.TARGET_CALLS)
        }

        viewModel.calls.observe(viewLifecycleOwner) { list ->
            fullList = list
            adapter.submitList(list)
        }

        binding.searchCalls.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterList(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        val q = (query ?: "").trim().lowercase()
        if (q.isEmpty()) {
            adapter.submitList(fullList)
            return
        }
        val filtered = fullList.filter { call ->
            call.callerId.lowercase().contains(q) ||
            call.callType.lowercase().contains(q) ||
            call.startTime.toString().contains(q) ||
            (call.endTime?.toString()?.contains(q) ?: false)
        }
        adapter.submitList(filtered)
    }

    fun setStatusFilter(status: String) {
        viewModel.setStatusFilter(status)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
