package com.app.alarmapp.presentation.ui

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.alarmapp.data.local.AlarmEntity
import com.app.alarmapp.databinding.ActivitySecondBinding
import com.app.alarmapp.extensions.formattedDate
import com.app.alarmapp.extensions.to12HFormattedTime
import com.app.alarmapp.util.AlarmReceiver
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val calender = Calendar.getInstance()

    private val viewModel: SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
        setupTimePickerDialog()
        setClickListeners()
    }

    @SuppressLint("SimpleDateFormat")
    private fun setData() {
        val date = Calendar.getInstance().time
        binding.timeTv.text = formattedDate(date)
    }

    @SuppressLint("SetTextI18n")
    private fun setupTimePickerDialog() {
        val mTimePicker: TimePickerDialog
        val mCurrentTime = Calendar.getInstance()
        val hour = mCurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mCurrentTime.get(Calendar.MINUTE)
        mTimePicker =
            TimePickerDialog(
                this,
                { _, hourOfDay, minuteOfDay ->
                    val hh = if (hourOfDay < 10) "0$hourOfDay" else hourOfDay
                    val mm = if (minute < 10) "0$minuteOfDay" else minuteOfDay
                    val time = "$hh:$mm"
                    binding.timeTv.text = time.to12HFormattedTime()
                    calender.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calender.set(Calendar.MINUTE, minuteOfDay)
                    calender.set(Calendar.SECOND, 0)
                }, hour, minute, false
            )
        binding.timeTv.setOnClickListener {
            mTimePicker.show()
        }
    }

    private fun setClickListeners() {
        binding.actionBack.setOnClickListener {
            finish()
        }
        binding.actionSave.setOnClickListener {
            startAlarm()
        }
    }

    private fun startAlarm() {
        if (binding.nameEt.text.isNullOrEmpty()) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show()
        } else {
            val alarmId = System.currentTimeMillis().toInt()
            viewModel.insert(
                AlarmEntity(
                    id = alarmId,
                    name = binding.nameEt.text.toString(),
                    isEnabled = true,
                    time = formattedDate(calender.time)
                )
            )
            val intent = Intent(this, AlarmReceiver::class.java)
            intent.putExtra("alarmId", alarmId)
            val pendingIntent = PendingIntent.getBroadcast(
                this.applicationContext, alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT
            )
            val alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                calender.timeInMillis, pendingIntent
            )
            Toast.makeText(this, "Alarm set for ${formattedDate(calender.time)}", Toast.LENGTH_LONG)
                .show()
            finish()
        }
    }
}