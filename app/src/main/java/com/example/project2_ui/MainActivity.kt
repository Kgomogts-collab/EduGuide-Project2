package com.example.project2_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.project2_ui.screen_notes.EduGuideNavGraph
import com.example.project2_ui.theme.EduGuideTheme
import com.example.project2_ui.theme.Parchment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EduGuideTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Parchment) {
                    EduGuideNavGraph()
                }
            }
        }
    }
}