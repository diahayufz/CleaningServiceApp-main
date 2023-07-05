package com.example.customerserviceapp.aplication

import android.app.Application
import com.example.customerserviceapp.repository.CleaningSRepository

class CleaningsApp: Application (){
    val database by lazy { CleaningsDatabase.getDatabase(this) }
    val repository by lazy { CleaningSRepository(database.cleaningsDao())}
}