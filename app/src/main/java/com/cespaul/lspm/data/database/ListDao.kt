package com.cespaul.lspm.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cespaul.lspm.model.Item

@Dao
interface ListDao {
    @Insert
    fun insertItem(item: Item)

    @Query("select * from itemList")
    fun selectItems(): List<Item>

    @Query("delete from itemList where id == :id")
    fun deleteItem(id: Int)

    @Update
    fun editItem(item: Item)
}