package com.superstudio.to_dolistapp.model

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.superstudio.to_dolistapp.R
import com.superstudio.to_dolistapp.databinding.ActivityMainBinding
import com.superstudio.to_dolistapp.databinding.ActivityTaskEditBinding
//import kotlinx.android.synthetic.main.activity_task_edit.*

class TaskEditActivity : AppCompatActivity() {

    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_edit)

        val taskId = intent.getIntExtra("task_id", -1)
        if (taskId != -1) {
            // Load task from ViewModel for editing
            // taskViewModel.getTaskById(taskId)
        }
        val binding: ActivityTaskEditBinding = ActivityTaskEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
            binding.saveButton.setOnClickListener {

            val title = binding.taskTitleInput.text.toString()
            val description = binding.taskDescriptionInput.text.toString()
                if(title.isEmpty())
                {
                    Toast.makeText(this,"Title can not be empty",Toast.LENGTH_SHORT).show()

                }
              else   {
                    val task = Task(title = title, description = description)

                    if (taskId == -1) {
                        taskViewModel.insert(task)
                    } else {
                        task.id = taskId
                        taskViewModel.update(task)
                    }
                }

            finish()
        }
    }
}
