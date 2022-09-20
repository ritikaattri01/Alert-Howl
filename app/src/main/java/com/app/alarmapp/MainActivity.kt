package com.app.alarmapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.alarmapp.adapter.AlarmAdapter
import com.app.alarmapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainAdapter: AlarmAdapter by lazy {
        AlarmAdapter()
    }
    private lateinit var binding: ActivityMainBinding

    private val viewModel: ListingViewModel by viewModels()

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
}