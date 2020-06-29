package com.example.lecione.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.lecione.modelo.Aluno

@Dao
interface AlunoDao {

    @Insert
    fun salva(aluno: Aluno) : Long

    @Query("SELECT * FROM Aluno")
    fun todos(): LiveData<List<Aluno>>
}