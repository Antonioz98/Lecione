package com.example.lecione.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.lecione.modelo.Aula

@Dao
interface AulaDao {

    @Insert
    fun salva(aula: Aula) : Long

    @Query("SELECT * FROM Aula")
    fun todos(): LiveData<List<Aula>>
}