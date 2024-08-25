package com.lukninja.nodeexplorer.service.model

data class Node(
    val alias: String,
    val capacity: Long,
    val channels: Int,
    val city: City?,
    val country: Country?,
    val firstSeen: Int,
    val publicKey: String,
    val updatedAt: Long,
)