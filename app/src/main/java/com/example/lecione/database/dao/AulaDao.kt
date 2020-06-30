package com.example.lecione.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lecione.modelo.Aula

@Dao
interface AulaDao {

    @Insert
    fun salva(aula: Aula): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun edita(aula: Aula) : Int

    @Update
    fun remove(aula: Aula)

    @Query("SELECT * FROM Aula")
    fun todos(): LiveData<List<Aula>>
}