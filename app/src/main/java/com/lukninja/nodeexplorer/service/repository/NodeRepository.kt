package com.lukninja.nodeexplorer.service.repository

import com.lukninja.nodeexplorer.service.model.Node
import com.lukninja.nodeexplorer.service.repository.remote.NodeService
import com.lukninja.nodeexplorer.service.repository.remote.RetrofitClient

class NodeRepository() {
    private val mRemote = RetrofitClient.createService(NodeService::class.java)

    suspend fun getLargerConnectedNodes(): List<Node>? {
        val response = mRemote.getLargerConnectedNodes()
        return if (response.isSuccessful) response.body() else listOf()
    }
}