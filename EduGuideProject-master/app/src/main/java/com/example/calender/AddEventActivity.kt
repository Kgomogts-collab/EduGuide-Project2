package com.example.calender

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import android.app.DatePickerDialog
import java.util.Calendar
import android.app.TimePickerDialog
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.content.Intent

class AddEventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_event)
        val saveEventButton = findViewById<Button>(R.id.save_event_button)
        val eventName = findViewById<EditText>(R.id.event_name)
        val selectDateButton = findViewById<Button>(R.id.select_date_button)
        val selectTimeButton = findViewById<Button>(R.id.select_time_button)
        val eventTypeSpinner = findViewById<Spinner>(R.id.event_type)
        val eventLocation = findViewById<EditText>(R.id.event_location)
        val eventDescription = findViewById<EditText>(R.id.event_description)

        val eventTypes = arrayOf(
            "Select Event Type",
            "Group Meeting",
            "Assignment",
            "Presentation",
            "Study Session",
            "Other"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            eventTypes
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        eventTypeSpinner.adapter = adapter

        saveEventButton.setOnClickListener {
            val name = eventName.text.toString().trim()
            val type = eventTypeSpinner.selectedItem.toString()
            val date = selectDateButton.text.toString()
            val time = selectTimeButton.text.toString()
            val location = eventLocation.text.toString().trim()
            val description = eventDescription.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter an event name", Toast.LENGTH_SHORT).show()
            } else if (type == "Select Event Type") {
                Toast.makeText(this, "Please select an event type", Toast.LENGTH_SHORT).show()
            } else if (date == "Select Date") {
                Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show()
            } else if (time == "Select Time") {
                Toast.makeText(this, "Please select a time", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, EventDetailsActivity::class.java)

                intent.putExtra("EVENT_NAME", name)
                intent.putExtra("EVENT_TYPE", type)
                intent.putExtra("EVENT_DATE", date)
                intent.putExtra("EVENT_TIME", time)
                intent.putExtra("EVENT_LOCATION", location)
                intent.putExtra("EVENT_DESCRIPTION", description)

                val eventPreferences = getSharedPreferences("EventPreferences", MODE_PRIVATE)

                eventPreferences.edit()
                    .putString("latest_event_name", name)
                    .putString("latest_event_date", date)
                    .putString("latest_event_time", time)
                    .apply()

                startActivity(intent)
            }
        }


        selectTimeButton.setOnClickListener {
            val calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(
                this,
                { _, selectedHour, selectedMinute ->
                    selectTimeButton.text =
                        String.format("%02d:%02d", selectedHour, selectedMinute)
                },
                hour,
                minute,
                true
            )

            timePicker.show()
        }

        selectDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    selectDateButton.text =
                        "$selectedDay/${selectedMonth + 1}/$selectedYear"
                },
                year,
                month,
                day
            )

            datePicker.show()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}