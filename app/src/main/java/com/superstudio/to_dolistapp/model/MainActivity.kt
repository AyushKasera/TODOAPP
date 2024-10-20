package com.superstudio.to_dolistapp.model

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat

import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.superstudio.to_dolistapp.R
import com.superstudio.to_dolistapp.databinding.ActivityMainBinding

import androidx.activity.viewModels
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskAdapter = TaskAdapter { task -> onTaskClicked(task) }
        binding.recyclerView.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }


        taskViewModel.allTasks.observe(this, Observer { tasks ->
            taskAdapter.submitList(tasks)
        })
            binding.fab.setOnClickListener {
            // Open task creation activity
            startActivity(Intent(this, TaskEditActivity::class.java))
        }
    }
    private fun onTaskClicked(task: Task) {
        // Open task edit screen
        val intent = Intent(this, TaskEditActivity::class.java)
        intent.putExtra("task_id", task.id)
        startActivity(intent)
    }
}