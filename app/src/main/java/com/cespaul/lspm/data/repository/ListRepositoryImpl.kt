package com.cespaul.lspm.data.repository

import android.content.Context
import androidx.room.Room
import com.cespaul.lspm.data.database.AppDatabase
import com.cespaul.lspm.model.Item
import java.util.*

class ListRepositoryImpl(context: Context/*, listDao: ListDao*/) : ListRepository {

    //private val repoListDao: ListDao = listDao

    private val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "main_repository"
    )
        .allowMainThreadQueries()
        .build()

    private var list = ArrayList<Item>()

    init {
        list.addAll(database.listDao().selectItems())
    }

    override fun addItem(item: Item) {
        database.listDao().insertItem(item)
        list.add(item)
    }

    override fun deleteItem(item: Item) {
        database.listDao().deleteItem(item.id)
        for (listItem in list) {
            if (listItem.id == item.id) {
                list.remove(listItem)
                return
            }
        }
    }

    override fun changeCheckItem(item: Item) {
        database.listDao().editItem(item)
    }

    override fun editItem(item: Item) {
        database.listDao().editItem(item)
    }

    override fun getList(): ArrayList<Item> {
        return list
    }

    override fun getItemCount(): Int = list.size

    override fun getItemAt(index: Int): Item = list[index]

}
