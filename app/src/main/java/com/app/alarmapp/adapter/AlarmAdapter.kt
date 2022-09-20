package com.app.alarmapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.alarmapp.data.local.AlarmEntity
import com.app.alarmapp.databinding.SingleItemAlarmBinding

class AlarmAdapter : ListAdapter<AlarmEntity, AlarmAdapter.MyViewHolder>(
    object : DiffUtil.ItemCallback<AlarmEntity>() {
        override fun areItemsTheSame(oldItem: AlarmEntity, newItem: AlarmEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AlarmEntity, newItem: AlarmEntity): Boolean {
            return oldItem == newItem
        }

    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = SingleItemAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class MyViewHolder(private val binding: SingleItemAlarmBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(alarm: AlarmEntity) {
            binding.itemAlarmTime.text = alarm.name
        }
    }
}