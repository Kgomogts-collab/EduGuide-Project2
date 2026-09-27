package com.example.project2.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun Dashboard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        val configuration = LocalConfiguration.current
        val screenWidthDp = configuration.screenWidthDp


        Text("EDU-GUIDE",modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
        CustomCentreSpacer(dp = screenWidthDp/2,composable = { CustomDropdownMenu() {} })
        Text("Task Progress")
        TaskProgress()
        Text("Meetings")
        Meetings()
        Members(
            names = listOf(
                "Kutlwano",
                "Lesedi",
                "Khumo",
                "Masego",
                "Boikanyo",
                "Letlotlo",
                "Ofentse",
                "Itumeleng",
                "Katlego"
            )
        )
    }
}


@Composable
fun CustomCentreSpacer(modifier: Modifier = Modifier,composable: @Composable () -> Unit,dp:Int) {
    Row() {
        Spacer(modifier = modifier.width(dp.dp))
        composable()


    }
}