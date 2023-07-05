package com.example.customerserviceapp.repository

import com.example.customerserviceapp.dao.CleaningSdao
import com.example.customerserviceapp.model.CleaningS
import kotlinx.coroutines.flow.Flow

class CleaningSRepository(private val cleaningSdao: CleaningSdao) {
    val allCleanings: Flow<List<CleaningS>> = cleaningSdao.getAllCleaningS()

    suspend fun insertCleaningS(cleaningS: CleaningS) {
        cleaningSdao.insertCleaningS(cleaningS)
    }

    suspend fun deleteCleaningS(cleaningS: CleaningS) {
        cleaningSdao.deleteCleaningS(cleaningS)
    }

    suspend fun updateCleaningS(cleaningS: CleaningS) {
        cleaningSdao.updateCleaningS(cleaningS)
    }
}