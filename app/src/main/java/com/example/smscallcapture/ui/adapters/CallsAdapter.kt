package com.example.smscallcapture.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smscallcapture.data.models.CallEntity
import com.example.smscallcapture.databinding.ItemCallBinding
import java.text.DateFormat

class CallsAdapter : ListAdapter<CallEntity, CallsAdapter.CallViewHolder>(Diff) {

    object Diff : DiffUtil.ItemCallback<CallEntity>() {
        override fun areItemsTheSame(oldItem: CallEntity, newItem: CallEntity): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CallEntity, newItem: CallEntity): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallViewHolder {
        val binding = ItemCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CallViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CallViewHolder(private val binding: ItemCallBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CallEntity) {
            binding.textCallerId.text = item.callerId
            binding.textType.text = item.callType
            binding.textStatus.text = item.status
            binding.textStart.text = DateFormat.getDateTimeInstance().format(item.startTime)
            binding.textEnd.text = item.endTime?.let { DateFormat.getDateTimeInstance().format(it) } ?: "-"
        }
    }
}




















