package com.rohitjakhar.mvvmtemplate.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dataModel")
data class DataEntity(
    @PrimaryKey
    val id: Int = -1,
    @ColumnInfo(name = "latitude")
    val latitude: Double? = -1.0,
    @ColumnInfo(name = "longitude")
    val longitude: Double? = -1.0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "iconBackgroundColor")
    val iconBackgroundColor: String = "",
    @ColumnInfo(name = "types")
    val types: String = "",
    @ColumnInfo(name = "vicinity")
    val vicinity: String = "",
    @ColumnInfo(name = "photo")
    val photo: String = "",
)
