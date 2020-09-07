package com.cespaul.lspm.ui.pages.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cespaul.lspm.R
import com.cespaul.lspm.model.ListItem
import com.cespaul.lspm.model.repository.ListRepository
import kotlinx.android.synthetic.main.item_list_row.view.*

class ListRvAdapter(
    private val listRepository: ListRepository,
    private val onItemClickListener: (position: Int, item: ListItem) -> Unit,
    private val onLongClickListener: (position: Int, item: ListItem, view: View) -> Boolean
) : RecyclerView.Adapter<ListRvAdapter.ListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVH {
        return ListVH(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_list_row,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int = listRepository.getItemCount()

    override fun onBindViewHolder(vh: ListVH, position: Int) {
        val listItem = listRepository.getItemAt(position)
        vh.itemName.text = listItem.nameItem
        vh.onItemClickListener = View.OnClickListener {
            onItemClickListener(position, listItem)
        }
        vh.onLongItemClickListener = View.OnLongClickListener {
            onLongClickListener(position, listItem, it)
        }
    }

    class ListVH(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var itemName: TextView = itemLayoutView.itemListTextView
        var onItemClickListener: View.OnClickListener? = null
        lateinit var onLongItemClickListener: View.OnLongClickListener

        init {
            itemLayoutView.setOnClickListener {
                onItemClickListener?.onClick(it)
            }
            itemLayoutView.setOnLongClickListener {
                onLongItemClickListener.onLongClick(it)
            }
        }
    }
}
