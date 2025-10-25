package com.lukninja.nodeexplorer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lukninja.nodeexplorer.service.model.Node
import com.lukninja.nodeexplorer.service.repository.NodeRepository
import com.lukninja.nodeexplorer.service.util.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NodeViewModel: ViewModel() {
    private val repository = NodeRepository()

    private val mNodeList = MutableLiveData<ApiResult<List<Node>>>()
    val nodeList: LiveData<ApiResult<List<Node>>> = mNodeList

    private val mError = MutableLiveData<String>()
    val error: LiveData<String> = mError

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mNodeList.postValue(ApiResult.Loading)
                mNodeList.postValue(repository.getLargerConnectedNodes())
            } catch (e: Exception){
                mNodeList.postValue(ApiResult.Error("Falha ao carregar os dados", e))
            }
        }
    }
}