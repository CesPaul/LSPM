package com.cespaul.lspm.data.repository

import com.cespaul.lspm.model.Item
import java.util.*

interface ListRepository {

    fun addItem(item: Item)

    fun editItem(item: Item)

    fun deleteItem(item: Item)

    fun changeCheckItem(item: Item)

    fun getList(): ArrayList<Item>

    fun getItemCount(): Int

    fun getItemAt(index: Int): Item

}