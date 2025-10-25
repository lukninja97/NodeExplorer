package com.lukninja.nodeexplorer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukninja.nodeexplorer.databinding.FragmentNodesListBinding
import com.lukninja.nodeexplorer.service.model.Node
import com.lukninja.nodeexplorer.service.util.ApiResult
import com.lukninja.nodeexplorer.view.adapter.NodeAdapter
import com.lukninja.nodeexplorer.viewmodel.NodeViewModel


class NodesListFragment : Fragment() {

    private var _binding: FragmentNodesListBinding? = null
    private val binding: FragmentNodesListBinding get() = _binding!!

    private val viewModel: NodeViewModel by viewModels()
    private lateinit var adapter: NodeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNodesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NodeAdapter(mutableListOf()) {
            //TODO Click item list
        }

        setupRecycler()
        observers()
        refreshList()

        binding.swipeRefresh.setOnRefreshListener {
            refreshList()
        }
    }

    private fun observers() {
        viewModel.nodeList.observe(viewLifecycleOwner) {
            when(it) {
                is ApiResult.Loading -> showLoading()

                is ApiResult.Success -> showNodes(it.data)

                is ApiResult.Error -> showError(it.message)
            }
        }
    }

    private fun showLoading() {
        binding.progress.visibility = View.VISIBLE
        binding.rvNodes.visibility = View.INVISIBLE
    }

    private fun showNodes(nodes: List<Node>) {
        adapter.updateNodes(nodes)
        binding.rvNodes.visibility = View.VISIBLE
        binding.progress.visibility = View.INVISIBLE
        binding.swipeRefresh.isRefreshing = false
        binding.imgError.visibility = View.GONE
        binding.tvError.visibility = View.GONE
    }

    private fun showError(message: String) {
        binding.swipeRefresh.isRefreshing = false
        binding.rvNodes.visibility = View.INVISIBLE
        binding.progress.visibility = View.INVISIBLE
        binding.imgError.visibility = View.VISIBLE
        binding.tvError.visibility = View.VISIBLE
        binding.tvError.text = message
    }

    private fun setupRecycler() {
        val recycler = binding.rvNodes
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
    }

    private fun refreshList() {
        binding.rvNodes.visibility = View.INVISIBLE
        binding.progress.visibility = View.VISIBLE
        viewModel.load()
    }
}