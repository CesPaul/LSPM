package com.cespaul.lspm.data.repository

import com.cespaul.lspm.data.database.ListDao
import com.cespaul.lspm.model.Item
import java.util.*

class ListRepositoryImpl(private val listDao: ListDao) : ListRepository {

    private var list = ArrayList<Item>()

    init {
        list.addAll(listDao.selectItems())
    }

    override fun addItem(item: Item) {
        listDao.insertItem(item)
        list.add(item)
    }

    override fun deleteItem(item: Item) {
        listDao.deleteItem(item.id)
        for (listItem in list) {
            if (listItem.id == item.id) {
                list.remove(listItem)
                return
            }
        }
    }

    override fun changeCheckItem(item: Item) {
        listDao.editItem(item)
    }

    override fun editItem(item: Item) {
        listDao.editItem(item)
    }

    override fun getList(): ArrayList<Item> {
        return list
    }

    override fun getItemCount(): Int = list.size

    override fun getItemAt(index: Int): Item = list[index]

}
