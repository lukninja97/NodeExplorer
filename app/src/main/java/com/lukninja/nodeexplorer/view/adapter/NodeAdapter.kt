package com.lukninja.nodeexplorer.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukninja.nodeexplorer.databinding.ListItemNodeBinding
import com.lukninja.nodeexplorer.extensions.toBitcoin
import com.lukninja.nodeexplorer.extensions.toDate
import com.lukninja.nodeexplorer.extensions.toDateUpdate
import com.lukninja.nodeexplorer.service.model.Node

class NodeAdapter(nodes: List<Node>?, private val clickListListener: (Node) -> Unit) :
    RecyclerView.Adapter<NodeAdapter.NodeViewHolder>() {

    private var nodesList: List<Node> = listOf()

    class NodeViewHolder(private val binding: ListItemNodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(node: Node, clickListListener: (Node) -> Unit) {
            val city = node.city?.ptBR ?: node.city?.en ?: ""

            binding.tvAlias.text = node.alias
            binding.tvCapacity.text = node.capacity.toBitcoin()
            binding.tvUpdated.text = "Atualizado em: " + node.updatedAt.toDateUpdate()
            binding.tvChannels.text = node.channels.toString()
            binding.tvKey.text = node.publicKey
            binding.tvPublicAt.text = "Público desde: " + node.firstSeen.toLong().toDate("dd/MM/yy")
            binding.tvCity.text = if (city.isNotEmpty()) "$city - " else ""
            binding.tvCountry.text = node.country?.ptBR ?: node.city?.en ?: ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
        val listItemNodeBinding =
            ListItemNodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NodeViewHolder(listItemNodeBinding)
    }

    override fun getItemCount(): Int {
        return nodesList.count()
    }

    override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
        holder.bind(nodesList[position], clickListListener)
    }

    fun updateNodes(list: List<Node>) {
        nodesList = list
        notifyDataSetChanged()
    }
}