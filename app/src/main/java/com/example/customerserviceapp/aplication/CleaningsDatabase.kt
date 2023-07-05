package com.example.customerserviceapp.aplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.customerserviceapp.dao.CleaningSdao
import com.example.customerserviceapp.model.CleaningS

@Database(entities = [CleaningS::class], version = 2, exportSchema = false)
abstract class CleaningsDatabase: RoomDatabase() {
    abstract fun cleaningsDao(): CleaningSdao

    companion object{
        private var INSTANCE: CleaningsDatabase? = null

        fun getDatabase(context: Context): CleaningsDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CleaningsDatabase::class.java,
                    "cleanings_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}
