package com.rohitjakhar.mvvmtemplate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rohitjakhar.mvvmtemplate.data.local.model.DataEntity

@Dao
interface AppDao {

    @Query("SELECT * FROM dataModel")
    suspend fun getAllFavoritesInDb(): List<DataEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertData(dataEntity: DataEntity)

}
