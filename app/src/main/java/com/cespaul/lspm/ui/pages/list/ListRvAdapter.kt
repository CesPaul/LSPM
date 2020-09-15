package com.cespaul.lspm.ui.pages.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cespaul.lspm.R
import com.cespaul.lspm.data.repository.ListRepository
import com.cespaul.lspm.model.Item
import kotlinx.android.synthetic.main.item_list_row.view.*

class ListRvAdapter(
    private val context: Context,
    private val listRepository: ListRepository,
    private val onItemClickListener: (position: Int, item: Item) -> Unit,
    private val onItemCheckBoxClickListener: (position: Int, item: Item) -> Unit,
    private val onLongClickListener: (position: Int, item: Item, view: View) -> Boolean
) : RecyclerView.Adapter<ListRvAdapter.ListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVH {
        return ListVH(
            LayoutInflater
                .from(context)
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
        vh.itemCheckBox.isChecked = listItem.isChecked
        if (vh.itemCheckBox.isChecked)
            vh.itemImage.setImageResource(R.drawable.list_check_24)
        else
            vh.itemImage.setImageResource(R.drawable.list_image_24)

        vh.onItemCheckBoxClickListener = View.OnClickListener {
            onItemCheckBoxClickListener(position, listItem)
        }
        vh.onItemClickListener = View.OnClickListener {
            onItemClickListener(position, listItem)
        }
        vh.onLongItemClickListener = View.OnLongClickListener {
            onLongClickListener(position, listItem, it)
        }
    }

    class ListVH(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var itemName: TextView = itemLayoutView.itemListTextView
        var itemImage: ImageView = itemLayoutView.itemListImage
        var itemCheckBox: CheckBox = itemLayoutView.itemListCheckBox
        var onItemClickListener: View.OnClickListener? = null
        var onItemCheckBoxClickListener: View.OnClickListener? = null
        lateinit var onLongItemClickListener: View.OnLongClickListener

        init {
            itemLayoutView.setOnClickListener {
                onItemClickListener?.onClick(it)
            }
            itemLayoutView.itemListCheckBox.setOnClickListener {
                onItemCheckBoxClickListener?.onClick(it)
            }
            itemLayoutView.setOnLongClickListener {
                onLongItemClickListener.onLongClick(it)
            }
        }
    }
}
