package com.example.calender

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView


class EventDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_event_details)
        val eventName = findViewById<TextView>(R.id.detail_event_name)
        val eventType = findViewById<TextView>(R.id.detail_event_type)
        val eventDate = findViewById<TextView>(R.id.detail_event_date)
        val eventTime = findViewById<TextView>(R.id.detail_event_time)
        val eventLocation = findViewById<TextView>(R.id.detail_event_location)
        val eventDescription = findViewById<TextView>(R.id.detail_event_description)

        val name = intent.getStringExtra("EVENT_NAME")
        val type = intent.getStringExtra("EVENT_TYPE")
        val date = intent.getStringExtra("EVENT_DATE")
        val time = intent.getStringExtra("EVENT_TIME")
        val location = intent.getStringExtra("EVENT_LOCATION")
        val description = intent.getStringExtra("EVENT_DESCRIPTION")

        eventName.text = name
        eventType.text = "Type: $type"
        eventDate.text = "Date: $date"
        eventTime.text = "Time: $time"
        eventLocation.text = "Location: $location"
        eventDescription.text = "Description: $description"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}