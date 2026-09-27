package com.example.project2.groupcollaborate

import java.util.UUID

data class Message(
    val senderName: String,
    val messageUID: String = UUID.randomUUID().toString(),
    val senderUID: String,
    val content: String,
    val time:Long  = System.currentTimeMillis(),
    val imageUID: String? =null,
    val videoUID: String? = null
)