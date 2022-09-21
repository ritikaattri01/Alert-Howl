package com.app.alarmapp.presentation.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.alarmapp.adapter.AlarmAdapter
import com.app.alarmapp.data.local.AlarmEntity
import com.app.alarmapp.databinding.ActivityMainBinding
import com.app.alarmapp.util.AlarmReceiver
import com.app.alarmapp.util.AlarmService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainAdapter: AlarmAdapter by lazy {
        AlarmAdapter(
            onSwitchClicked = { it, isChecked ->
                if (isChecked) {
                    Toast.makeText(this, "Enable", Toast.LENGTH_LONG).show()
                    it?.let { it1 ->
//                        startAlarm(it1)
                        viewModel.update(it)
                    }

                } else {
                    cancelAlarm(it!!)
                    val intentService = Intent(applicationContext, AlarmService::class.java)
                    applicationContext.stopService(intentService)
                    Toast.makeText(this, "Disable", Toast.LENGTH_LONG).show()
                    viewModel.update(it)
                }
            }
        )
    }
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observe()
        setRecyclerView()
        setClickListeners()
    }

    private fun setRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = mainAdapter
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.getAlarms().observe(
                this@MainActivity
            ) {
                mainAdapter.submitList(it)
            }
        }
    }

    private fun setClickListeners() {
        binding.actionAdd.setOnClickListener {
            val i = Intent(applicationContext, SecondActivity::class.java)
            startActivity(i)
        }
    }

    private fun cancelAlarm(alarm: AlarmEntity) {
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            alarm.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }
}