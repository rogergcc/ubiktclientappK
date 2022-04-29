package com.rohitjakhar.mvvmtemplate.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.rohitjakhar.mvvmtemplate.data.local.model.DataModel

@Dao
interface AppDao {

    @Query("SELECT * FROM dataModel")
    suspend fun getAllFavoritesInDb(): List<DataModel>

    @Insert(onConflict = REPLACE)
    suspend fun insertData(dataModel: DataModel)

}
