package com.cespaul.lspm.model.repository

import com.cespaul.lspm.model.ListItem
import java.util.*

interface ListRepository {

    fun addItem(item: ListItem)

    fun editItem(item: ListItem)

    fun deleteItem(item: ListItem)

    fun getList(): ArrayList<ListItem>

    fun getItemCount(): Int

    fun getItemAt(index: Int): ListItem

}