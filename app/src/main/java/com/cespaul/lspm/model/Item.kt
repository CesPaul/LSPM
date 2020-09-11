package com.cespaul.lspm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itemList")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "nameItem")
    var nameItem: String
)