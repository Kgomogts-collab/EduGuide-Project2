package com.example.project2.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Meetings(modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ){
        item{ MeetingItem(name = "Subject",leader = "Assigne",date = "Date")}
        item{ MeetingItem(name = "Add a button to the...",leader = "UI Developer",date = "1 Sep")}
        item{ MeetingItem(name = "Stakeholder Meeting",leader = "Project Leader",date = "6 Sep")}
        item{ MeetingItem(name = "Design login screen",leader = "UI Developer",date = "25 Sep")}
        item{ MeetingItem(name = "Stakeholder Meeting",leader = "Project Leader",date = "1 Oct")}
        item{ MeetingItem(name = "Implement encryption",leader = "Security Developer",date = "18 Oct")}
        item{ MeetingItem(name = "Design Database",leader = "Data team",date = "23 Oct")}
        item{ MeetingItem(name = "Final Stakeholder...",leader = "Project Leader",date = "1 Nov")}

    }
    
}

@Composable
fun MeetingItem(modifier: Modifier = Modifier,name: String,leader: String,date: String) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(name)
            Text(leader)
            Text(date)
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.secondary)
    }


}