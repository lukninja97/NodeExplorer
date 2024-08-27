package com.lukninja.nodeexplorer.service.repository.remote

import com.lukninja.nodeexplorer.service.model.Node
import retrofit2.Response
import retrofit2.http.GET

interface NodeService {

    @GET("api/v1/lightning/nodes/rankings/connectivity")
    suspend fun getLargerConnectedNodes(): Response<List<Node>>

}