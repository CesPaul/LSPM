package com.cespaul.lspm.model.repository

import com.cespaul.lspm.model.ListItem
import java.util.*

class ListRepositoryImpl : ListRepository {

    private var list = ArrayList<ListItem>()

    override fun addItem(item: ListItem) {
        list.add(item)
    }

    override fun deleteItem(item: ListItem) {
        for (listItem in list) {
            if (listItem.id == listItem.id) {
                list.remove(listItem)
                return
            }
        }
    }

    override fun editItem(item: ListItem) {
        // Todo: Реализовать редактирование элемента, когда будет БД.
    }

    override fun getList(): ArrayList<ListItem> {
        return list
    }

    override fun getItemCount(): Int = list.size

    override fun getItemAt(index: Int): ListItem = list[index]

}
