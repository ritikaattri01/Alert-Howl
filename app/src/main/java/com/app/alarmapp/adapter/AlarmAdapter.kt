package com.app.alarmapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.alarmapp.data.local.AlarmEntity
import com.app.alarmapp.databinding.SingleItemAlarmBinding
import com.google.android.material.snackbar.Snackbar


class AlarmAdapter(private var onSwitchClicked: (AlarmEntity?) -> Unit) :
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
        return MyViewHolder(binding, onSwitchClicked = {
            onSwitchClicked(getItem(it))
        })
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class MyViewHolder(
        private val binding: SingleItemAlarmBinding,
        val onSwitchClicked: ((Int) -> Unit)?
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.actionSwitch.setOnClickListener {
                onSwitchClicked?.invoke(adapterPosition)
            }
        }

        fun setData(alarm: AlarmEntity) {
            binding.itemAlarmTime.text = alarm.time
            binding.actionSwitch.isChecked = alarm.isEnabled == true
        }
    }
}