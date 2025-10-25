package com.lukninja.nodeexplorer.service.repository

import com.lukninja.nodeexplorer.service.model.Node
import com.lukninja.nodeexplorer.service.repository.remote.NodeService
import com.lukninja.nodeexplorer.service.repository.remote.RetrofitClient
import com.lukninja.nodeexplorer.service.util.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NodeRepository {
    private val mRemote = RetrofitClient.createService(NodeService::class.java)

    suspend fun getLargerConnectedNodes(): ApiResult<List<Node>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = mRemote.getLargerConnectedNodes()
                ApiResult.Success(response.body() ?: listOf())
            } catch (e: Exception) {
                ApiResult.Error("Falha ao carregar usuários", e)
            }
        }
    }
}