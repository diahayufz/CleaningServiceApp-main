package com.example.customerserviceapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.customerserviceapp.model.CleaningS
import kotlinx.coroutines.flow.Flow

@Dao
interface CleaningSdao {
    @Query("SELECT * FROM `CleaningS_table` ORDER BY name ASC ")
    fun getAllCleaningS():Flow<List<CleaningS>>

    @Insert
    suspend fun insertCleaningS(Cleanings: CleaningS)

    @Delete
    suspend fun deleteCleaningS(Cleanings: CleaningS)

    @Update fun updateCleaningS(Cleanings: CleaningS)
}