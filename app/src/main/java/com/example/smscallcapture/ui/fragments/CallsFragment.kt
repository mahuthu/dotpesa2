package com.example.smscallcapture.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

        val statuses = resources.getStringArray(R.array.status_filter)
        binding.spinnerStatus.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, statuses)
        binding.spinnerStatus.setSelection(0)
        binding.spinnerStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, v: View?, position: Int, id: Long) {
                viewModel.setStatusFilter(statuses[position])
            }
            override fun onNothingSelected(parent: AdapterView<*>) { }
        }

        binding.fabRefreshCalls.setOnClickListener {
            SyncService.startSync(requireContext(), SyncService.TARGET_CALLS)
        }

        viewModel.calls.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
