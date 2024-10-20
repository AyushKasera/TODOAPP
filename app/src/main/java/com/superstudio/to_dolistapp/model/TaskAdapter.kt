package com.superstudio.to_dolistapp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.superstudio.to_dolistapp.R
import com.superstudio.to_dolistapp.databinding.ActivityTaskEditBinding

//import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter(private val onTaskClicked: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var taskList = emptyList<Task>()

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTitle: TextView = itemView.findViewById(R.id.task_title)
        val taskCheckBox: CheckBox = itemView.findViewById(R.id.task_completed)
        fun bind(task: Task, onTaskClicked: (Task) -> Unit) {
            taskTitle.text = task.title
            taskCheckBox.isChecked = task.isCompleted
            taskCheckBox.setOnClickListener {
                task.isCompleted = !task.isCompleted
                onTaskClicked(task)
            }
            itemView.setOnClickListener { onTaskClicked(task) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        val binding = ActivityTaskEditBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task, onTaskClicked)
    }

    override fun getItemCount() = taskList.size

    fun submitList(list: List<Task>) {
        taskList = list
        notifyDataSetChanged()
    }
}
