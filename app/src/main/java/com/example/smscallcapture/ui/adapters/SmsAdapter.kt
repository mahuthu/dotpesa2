package com.example.smscallcapture.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.smscallcapture.data.models.SmsEntity
import com.example.smscallcapture.databinding.ItemSmsBinding
import java.text.DateFormat

class SmsAdapter : ListAdapter<SmsEntity, SmsAdapter.SmsViewHolder>(Diff) {

    object Diff : DiffUtil.ItemCallback<SmsEntity>() {
        override fun areItemsTheSame(oldItem: SmsEntity, newItem: SmsEntity): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: SmsEntity, newItem: SmsEntity): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsViewHolder {
        val binding = ItemSmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SmsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SmsViewHolder(private val binding: ItemSmsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SmsEntity) {
            binding.textSender.text = item.sender
            binding.textMessage.text = item.message
            binding.textStatus.text = item.status
            binding.textDate.text = DateFormat.getDateTimeInstance().format(item.receivedDate)
        }
    }
}




















