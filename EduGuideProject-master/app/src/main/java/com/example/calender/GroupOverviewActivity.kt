package com.example.calender

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView

class GroupOverviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_group_overview)
        val viewCalendarButton = findViewById<Button>(R.id.view_calendar_button)
        val addEventButton = findViewById<Button>(R.id.add_event_button)
        val taskOne = findViewById<CheckBox>(R.id.task_one)
        val taskTwo = findViewById<CheckBox>(R.id.task_two)
        val taskThree = findViewById<CheckBox>(R.id.task_three)
        val taskFour = findViewById<CheckBox>(R.id.task_four)
        val sharedPreferences = getSharedPreferences("TaskPreferences", MODE_PRIVATE)
        val progressBar = findViewById<ProgressBar>(R.id.project_progress_bar)
        val progressText = findViewById<TextView>(R.id.progress_text)
        val eventOne = findViewById<TextView>(R.id.event_one)
        val eventTwo = findViewById<TextView>(R.id.event_two)
        val eventPreferences = getSharedPreferences("EventPreferences", MODE_PRIVATE)

        val latestEventName =
            eventPreferences.getString("latest_event_name", null)

        val latestEventDate =
            eventPreferences.getString("latest_event_date", null)

        val latestEventTime =
            eventPreferences.getString("latest_event_time", null)

        if (latestEventName != null &&
            latestEventDate != null &&
            latestEventTime != null) {

            eventOne.text =
                "$latestEventName - $latestEventDate, $latestEventTime"
        }

        val memberOneContribution =
            findViewById<TextView>(R.id.member_one_contribution)

        val memberTwoContribution =
            findViewById<TextView>(R.id.member_two_contribution)

        val memberThreeContribution =
            findViewById<TextView>(R.id.member_three_contribution)

        val memberFourContribution =
            findViewById<TextView>(R.id.member_four_contribution)

        taskOne.isChecked = sharedPreferences.getBoolean("task_one", false)
        taskTwo.isChecked = sharedPreferences.getBoolean("task_two", false)
        taskThree.isChecked = sharedPreferences.getBoolean("task_three", false)
        taskFour.isChecked = sharedPreferences.getBoolean("task_four", false)


        fun updateProgress() {
            var completedTasks = 0

            if (taskOne.isChecked) completedTasks++
            if (taskTwo.isChecked) completedTasks++
            if (taskThree.isChecked) completedTasks++
            if (taskFour.isChecked) completedTasks++

            val progress = completedTasks * 25

            progressBar.progress = progress
            progressText.text = "$completedTasks of 4 Tasks Completed"
        }

        fun updateContributions() {
            memberOneContribution.text =
                if (taskOne.isChecked) "Contribution: 100%" else "Contribution: 0%"

            memberTwoContribution.text =
                if (taskTwo.isChecked) "Contribution: 100%" else "Contribution: 0%"

            memberThreeContribution.text =
                if (taskThree.isChecked) "Contribution: 100%" else "Contribution: 0%"

            memberFourContribution.text =
                if (taskFour.isChecked) "Contribution: 100%" else "Contribution: 0%"
        }

        taskOne.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit()
                .putBoolean("task_one", isChecked)
                .apply()

            updateProgress()
            updateContributions()
        }

        taskTwo.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit()
                .putBoolean("task_two", isChecked)
                .apply()

            updateProgress()
            updateContributions()
        }

        taskThree.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit()
                .putBoolean("task_three", isChecked)
                .apply()

            updateProgress()
            updateContributions()
        }

        taskFour.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit()
                .putBoolean("task_four", isChecked)
                .apply()

            updateProgress()
            updateContributions()
        }

        updateProgress()
        updateContributions()

        addEventButton.setOnClickListener {
            val intent = Intent(this, AddEventActivity::class.java)
            startActivity(intent)
        }

        viewCalendarButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}