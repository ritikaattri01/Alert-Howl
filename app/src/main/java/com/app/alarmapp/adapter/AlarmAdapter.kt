package com.app.alarmapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.alarmapp.data.local.AlarmEntity
import com.app.alarmapp.databinding.SingleItemAlarmBinding


class AlarmAdapter(private var onSwitchClicked: (AlarmEntity?, isChecked: Boolean) -> Unit) :
    ListAdapter<AlarmEntity, AlarmAdapter.MyViewHolder>(
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
        val binding =
            SingleItemAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onSwitchClicked = { it, isChecked ->
            onSwitchClicked(getItem(it), isChecked)
        })
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class MyViewHolder(
        private val binding: SingleItemAlarmBinding,
        val onSwitchClicked: ((Int, Boolean) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.actionSwitch.setOnCheckedChangeListener { _, isChecked ->
                onSwitchClicked?.invoke(adapterPosition, isChecked)
            }
        }

        fun setData(alarm: AlarmEntity) {
            binding.itemAlarmTime.text = alarm.time
            binding.actionSwitch.isChecked = alarm.isEnabled == true
        }
    }
}