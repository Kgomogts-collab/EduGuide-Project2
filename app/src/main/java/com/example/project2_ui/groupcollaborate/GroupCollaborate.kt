package com.example.project2.groupcollaborate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddReaction
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun GroupCollaborate(
    modifier: Modifier = Modifier,
    messages: List<Message>,
    userUID: String,
    sendText: (String) -> Unit) {

    var text by rememberSaveable { mutableStateOf("") }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(items = messages.sortedBy { it.time },key ={ it.messageUID }){
            MessageItem(userUID = userUID,message = it)
        }
        item{
            OutlinedTextField(
                label = { Text("message")},
                value = text,
                onValueChange = { text = it},
                leadingIcon = { Icon(Icons.Default.AddReaction,"Add reaction")},
                trailingIcon = {
                    IconButton(
                        onClick = {
                            sendText(text)
                            text = ""
                        }
                    ) { Icon(Icons.Default.Send,"Send text")}
                }
            )
        }
    }
}

@Composable
fun MessageItem(
    modifier: Modifier = Modifier,
    userUID: String,
    message: Message) {
    if(message.senderUID == userUID){
        Row(){
            Icon(Icons.Default.AccountCircle,"Message sender profile picture",modifier = Modifier.size(30.dp))
            Card(
                shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp,bottomEnd = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Blue,
                    contentColor = Color.Black
                )
            ) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(message.senderName, fontWeight = FontWeight.Bold)
                    Text(message.content)
                }
            }
        }
    } else{
        Row(){

            Card(
                shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp,bottomEnd = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray,
                    contentColor = Color.Black
                )
            ) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(message.senderName, fontWeight = FontWeight.Bold)
                    Text(message.content)
                }
            }
            Icon(Icons.Default.AccountCircle,"Message sender profile picture",modifier = Modifier.size(30.dp))
        }
    }
    
}

