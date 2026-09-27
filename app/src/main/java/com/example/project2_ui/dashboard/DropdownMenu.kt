package com.example.project2.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

enum class Groups{
    MULTIMEDIA,
    PROJECT2,
    COMMUNICATIONS,
    ISA
}
@Composable
fun CustomDropdownMenu(modifier: Modifier = Modifier,selected:(Groups) -> Unit) {
    var expanded by rememberSaveable{ mutableStateOf(false)}

    Box(
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick ={
                //
                expanded = !expanded
            }
        ) {
            Icon(Icons.Default.ArrowDropDown,"Select group")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false
            }) {
            DropdownMenuItem(
                text = { Text("Multimedia Group")},
                onClick = { selected(Groups.MULTIMEDIA)}
            )
            DropdownMenuItem(
                text = { Text("Project2 Group")},
                onClick = { selected(Groups.PROJECT2)}
            )
            DropdownMenuItem(
                text = { Text("ProfCom Group")},
                onClick = { selected(Groups.COMMUNICATIONS)}
            )
            DropdownMenuItem(
                text = { Text("ISA Group")},
                onClick = { selected(Groups.ISA)}
            )
        }
    }


}