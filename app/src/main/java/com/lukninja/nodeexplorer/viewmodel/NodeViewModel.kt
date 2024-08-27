package com.lukninja.nodeexplorer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lukninja.nodeexplorer.service.model.Node
import com.lukninja.nodeexplorer.service.repository.NodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NodeViewModel: ViewModel() {
    private val repository = NodeRepository()

    private val mNodeList = MutableLiveData<List<Node>>()
    val nodeList: LiveData<List<Node>> = mNodeList

    private val mError = MutableLiveData<String>()
    val error: LiveData<String> = mError

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getLargerConnectedNodes()?.let { nodeList ->
                    if (nodeList.isNotEmpty()){
                        mNodeList.postValue(nodeList)
                    } else {
                        mError.postValue("Bad request")
                    }
                } ?: run {
                    mError.postValue("Empty list")
                }

            } catch (e: Exception){
                mError.postValue(e.message)
            }
        }
    }
}